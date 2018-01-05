package com.adv.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.exceptions.MyException;
import com.adv.models.Terminal;
import com.adv.models.Vote;
import com.adv.models.VoteItem;
import com.adv.models.VoteRecord;
import com.adv.repository.TerminalRepository;
import com.adv.repository.VoteItemRepository;
import com.adv.repository.VoteRecordRepository;
import com.adv.repository.VoteRepository;
import com.adv.service.VoteItemService;
import com.adv.utils.DataWrapper;

@Service
public class VoteItemServiceImpl implements VoteItemService {
	
	@Autowired
	VoteItemRepository voteItemRepository;
	
	@Autowired
	VoteRepository voteRepository;
	
	@Autowired
	VoteRecordRepository voteRecordRepository;
	
	@Autowired
	TerminalRepository terminalRepository;

	@Override
	public DataWrapper<VoteItem> add(VoteItem voteItem, Long voteId) {
		if (voteItem.getName() == null || voteItem.getName().equals("")) {
			throw new MyException("投票选项为空");
		}
		
		if (voteItem.getImgSrc() == null || voteItem.getImgSrc().equals("")) {
			throw new MyException("投票图片为空");
		}
		Vote vote = voteRepository.findOne(voteId);
		if (vote == null) {
			throw new MyException("投票项目不存在");
		}
		voteItem.setId(null);
		voteItem.setResult(0);
		voteItem.setVoteId(voteId);
		try {
			voteItemRepository.save(voteItem);
		} catch (Exception e) {
			throw new MyException("数据库错误");
		}
		DataWrapper<VoteItem> dataWrapper = new DataWrapper<VoteItem>();
		dataWrapper.setData(voteItem);
		return dataWrapper;
	}

	@Override
	public DataWrapper<VoteItem> update(VoteItem voteItem, Long voteItemId) {
		VoteItem voteItemInDB = voteItemRepository.findOne(voteItemId);
		if (voteItemInDB == null) {
			throw new MyException("投票选项不存在");
		}
		if (voteItem.getName() != null && !voteItem.getName().equals("")) {
			voteItemInDB.setName(voteItem.getName());
		}
		if (voteItem.getImgSrc() != null && !voteItem.getImgSrc().equals("")) {
			voteItemInDB.setImgSrc(voteItem.getImgSrc());
		}
		try {
			voteItemRepository.save(voteItemInDB);
		} catch (Exception e) {
			throw new MyException("数据库错误");
		}
		DataWrapper<VoteItem> dataWrapper = new DataWrapper<VoteItem>();
		dataWrapper.setData(voteItemInDB);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> delete(Long voteItemId) {
		VoteItem voteItem = voteItemRepository.findOne(voteItemId);
		if (voteItem == null) {
			throw new MyException("投票选项不存在");
		}
		try {
			voteItemRepository.delete(voteItem);
		} catch (Exception e) {
			throw new MyException("数据库错误");
		}
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> vote(String mac, Long voteId, VoteRecord[] voteRecords) {
		
		Terminal terminal = terminalRepository.findByMac(mac);
		if (terminal == null) {
			throw new MyException("终端不存在");
		}
		
		Vote vote = voteRepository.findOne(voteId);
		if (vote == null) {
			throw new MyException("投票项目不存在");
		}
		
		List<Long> availableVoteItemIds = new ArrayList<>();
		for (VoteItem voteItem : vote.getVoteItems()) {//保存这个vote中所有可以被投票的投票选项id
			availableVoteItemIds.add(voteItem.getId());
		}
		
		
		
		Map<Long, Integer> numberRecord = new HashMap<>();//voteItemId-count
		for (VoteRecord voteRecord : voteRecords) {
			if (voteRecord.getVoteItemId() == null) {
				throw new MyException("投票选项id为空");
			}
			if (voteRecord.getVoteNumber() == null || voteRecord.getVoteNumber() <= 0) {
				throw new MyException("票数为空或小于等于0");
			}
			if ( !availableVoteItemIds.contains(voteRecord.getVoteItemId()) ) {//前端传过来的投票选项id必须归属于这个vote中，否则报错
				throw new MyException("投票选项不归属于该投票项目");
			}
			
			Integer number = numberRecord.get(voteRecord.getVoteItemId());
			if (number == null) {
				numberRecord.put(voteRecord.getVoteItemId(), voteRecord.getVoteNumber());
			} else {
				numberRecord.put(voteRecord.getVoteItemId(), voteRecord.getVoteNumber() + number);
			}
			
			
			voteRecord.setId(null);
			voteRecord.setMac(mac);
			voteRecord.setVoteId(voteId);
			
		}
		
		
		
		List<VoteRecord> list = voteRecordRepository.findByMacAndVoteId(mac, voteId);//已经投过的票
		int allCanVoteNumber = 0; //已投过的票的总数
		
		for(VoteRecord voteRecord : list) {
			Integer number = numberRecord.get(voteRecord.getVoteItemId());
			
			if (number != null) {
				numberRecord.put(voteRecord.getVoteItemId(), voteRecord.getVoteNumber() + number);
			}
			
			allCanVoteNumber += voteRecord.getVoteNumber();
		}
		
		
		for (Map.Entry<Long, Integer> entry: numberRecord.entrySet()) {
			Integer number = entry.getValue();
			if (number > vote.getEachItemCanVoteNumber()) {
				throw new MyException("每项可投票数超出");
			}
			allCanVoteNumber += number;
		}
		if (allCanVoteNumber > vote.getAllCanVoteNumber()) {
			throw new MyException("总共可投票数超出");
		}
		try {
			voteRecordRepository.save(Arrays.asList(voteRecords));
		} catch (Exception e) {
			throw new MyException("数据库错误");
		}
		
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> refreshAll() {
		try {
			voteRecordRepository.deleteAllInBatch();
		} catch (Exception e) {
			throw new MyException("数据库错误");
		}
		
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> refreshVoteItem(Long voteId) {
		try {
			voteRecordRepository.deleteByVoteId(voteId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库错误");
		}
		
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}

}
