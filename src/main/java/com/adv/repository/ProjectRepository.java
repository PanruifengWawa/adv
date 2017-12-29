package com.adv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.adv.models.Project;

public interface ProjectRepository  extends JpaRepository<Project, Long> {
	
	@Transactional
	@Query(value = "delete from project  where id = :projectId",nativeQuery = true)
	@Modifying
	void deleteByProjectId( @Param("projectId") Long projectId);
}
