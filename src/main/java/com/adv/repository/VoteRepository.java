package com.adv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.adv.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	
	@Transactional
	@Query(value = "delete from vote  where id = :voteId",nativeQuery = true)
	@Modifying
	void deleteByVoteId( @Param("voteId") Long voteId);

}
