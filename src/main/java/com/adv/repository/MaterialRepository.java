package com.adv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adv.models.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {

}
