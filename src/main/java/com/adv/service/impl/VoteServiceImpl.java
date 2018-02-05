package com.adv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.exceptions.MyException;
import com.adv.models.Vote;
import com.adv.repository.VoteRepository;
import com.adv.service.VoteService;
import com.adv.utils.DataWrapper;

@Service
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	VoteRepository voteRepository;

	@Override
	public DataWrapper<List<Vote>> getVoteList() {
		DataWrapper<List<Vote>> dataWrapper = new DataWrapper<List<Vote>>();
		List<Vote> list = voteRepository.findAll();
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Vote> add(Vote vote) {
		if (vote.getName() == null || vote.getName().equals("")) {
			throw new MyException("投票项目名称为空");
		}
		
		if (vote.getAllCanVoteNumber() == null || vote.getAllCanVoteNumber() <= 0) {
			throw new MyException("终端可投总票数为空或小于零");
		}
		
		if (vote.getEachItemCanVoteNumber() == null || vote.getEachItemCanVoteNumber() <= 0) {
			throw new MyException("终端每项最多可投票数为空或小于零");
		}
		
		vote.setId(null);
		try {
			voteRepository.save(vote);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库错误");
		}
		
		DataWrapper<Vote> dataWrapper = new DataWrapper<Vote>();
		dataWrapper.setData(vote);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> delete(Long voteId) {
		// TODO Auto-generated method stub
		try {
			voteRepository.deleteByVoteId(voteId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库错误");
		}
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}

	@Override
	public DataWrapper<Vote> getById(Long voteId) {
		DataWrapper<Vote> dataWrapper = new DataWrapper<Vote>();
		Vote vote = voteRepository.findOne(voteId);
		dataWrapper.setData(vote);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Vote> update(Vote vote, Long voteId) {
		Vote voteInDB = voteRepository.findOne(voteId);
		if (voteInDB == null) {
			throw new MyException("投票项目不存在");
		}
		if (vote.getAllCanVoteNumber() != null && vote.getAllCanVoteNumber() > 0) {
			voteInDB.setAllCanVoteNumber(vote.getAllCanVoteNumber());
		}
		
		if (vote.getEachItemCanVoteNumber() != null && vote.getEachItemCanVoteNumber() > 0) {
			voteInDB.setEachItemCanVoteNumber(vote.getEachItemCanVoteNumber());
		}
		
		try {
			voteRepository.save(voteInDB);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库错误");
		}
		DataWrapper<Vote> dataWrapper = new DataWrapper<Vote>();
		dataWrapper.setData(voteInDB);
		return dataWrapper;
	}

}
