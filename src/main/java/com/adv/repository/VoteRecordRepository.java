package com.adv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.adv.models.VoteRecord;

public interface VoteRecordRepository extends JpaRepository<VoteRecord, Long> {
	List<VoteRecord> findByMacAndVoteId(String mac, Long voteId);

	@Transactional
	@Query(value = "delete from vote_record where vote_id = :voteId",nativeQuery = true)
	@Modifying
	void deleteByVoteId( @Param("voteId") Long voteId);
}
