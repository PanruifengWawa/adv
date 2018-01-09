package com.adv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.adv.models.VoteItem;

public interface VoteItemRepository extends JpaRepository<VoteItem, Long> {
	
	@Transactional
	@Query(value = "update vote_item set result = :result  where vote_id = :voteId",nativeQuery = true)
	@Modifying
	void updateResultByVoteId(@Param("result") Integer result, @Param("voteId") Long voteId);
	
	
	@Transactional
	@Query(value = "update vote_item set result = :result",nativeQuery = true)
	@Modifying
	void updateResultAll(@Param("result") Integer result);
	
}
