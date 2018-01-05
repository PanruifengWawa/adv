package com.adv.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message {
	private Long id;
	private String mac;
	private String terminalName;
	private Integer state;
	private String content;
	
	
	@Basic
    @Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Basic
    @Column(name = "mac")
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	@Basic
    @Column(name = "terminal_name")
	public String getTerminalName() {
		return terminalName;
	}
	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}
	
	
	@Basic
    @Column(name = "state")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	
}
