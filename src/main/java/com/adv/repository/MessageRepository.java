package com.adv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adv.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findByStateOrderByIdDesc(Integer state);
	
	List<Message> findAllByOrderByIdDesc();

	
	List<Message> findByIdInAndState(List<Long> id, Integer state);
}
