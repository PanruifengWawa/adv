package com.adv.service;

import java.util.List;

import com.adv.models.Vote;
import com.adv.utils.DataWrapper;

public interface VoteService {
	
	DataWrapper<List<Vote>> getVoteList();
	
	DataWrapper<Vote> add(Vote vote);
	
	DataWrapper<Void> delete(Long voteId);
	
	DataWrapper<Vote> getById(Long voteId);

}
