package com.adv.service;

import org.springframework.transaction.annotation.Transactional;

import com.adv.models.VoteItem;
import com.adv.models.VoteRecord;
import com.adv.utils.DataWrapper;

public interface VoteItemService {
	
	DataWrapper<VoteItem> add(VoteItem voteItem, Long voteId);

	DataWrapper<VoteItem> update(VoteItem voteItem, Long voteItemId);
	
	DataWrapper<Void> delete(Long voteItemId);
	
	DataWrapper<Void> vote(String mac, Long voteId, VoteRecord[] voteRecords);
	
	@Transactional
	DataWrapper<Void> refreshAll();
	
	
	@Transactional
	DataWrapper<Void> refreshVoteItem(Long voteId);
	
	
	DataWrapper<VoteItem> updateResult(Long voteItemId, Integer result);
	
	
	
	
}
