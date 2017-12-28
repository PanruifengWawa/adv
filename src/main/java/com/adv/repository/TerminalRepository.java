package com.adv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adv.models.Terminal;


public interface TerminalRepository extends JpaRepository<Terminal, String> {
	Terminal findByMac(String mac);

}
