package com.adv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adv.models.VoteItem;

public interface VoteItemRepository extends JpaRepository<VoteItem, Long> {
	
}
