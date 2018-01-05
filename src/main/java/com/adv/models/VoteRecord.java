package com.adv.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vote_record")
public class VoteRecord {
	private Long id;
	private String mac;
	private Long voteId;
	private Long voteItemId;
	private Integer voteNumber;
	
	
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
    @Column(name = "vote_id")
	public Long getVoteId() {
		return voteId;
	}
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}
	
	@Basic
    @Column(name = "vote_item_id")
	public Long getVoteItemId() {
		return voteItemId;
	}
	public void setVoteItemId(Long voteItemId) {
		this.voteItemId = voteItemId;
	}
	
	@Basic
    @Column(name = "vote_number")
	public Integer getVoteNumber() {
		return voteNumber;
	}
	public void setVoteNumber(Integer voteNumber) {
		this.voteNumber = voteNumber;
	}
	
	
}
