package com.adv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adv.models.Version;

public interface VersionRepository extends JpaRepository<Version, Long> {
	List<Version> findAllByOrderByIdDesc();
}
