package com.adv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adv.models.BgImage;

public interface BgImageRepository extends JpaRepository<BgImage, Long> {
	BgImage findByType(Integer type);
}
