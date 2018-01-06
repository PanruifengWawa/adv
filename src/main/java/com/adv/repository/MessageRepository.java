package com.adv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.adv.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findByStateOrderByIdDesc(Integer state);
	
	List<Message> findAllByOrderByIdDesc();

	
	List<Message> findByIdInAndState(List<Long> id, Integer state);
	
	@Transactional
	@Query(value = "delete from message where id in :ids",nativeQuery = true)
	@Modifying
	void deleteByIds(@Param("ids") List<Long> ids);
	
}
