package com.adv.models;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote {

	private Long id;
	private String name;
	private Integer allCanVoteNumber;
	private Integer eachItemCanVoteNumber;
	
	private List<VoteItem> voteItems;
	
	
	@OneToMany
	@JoinColumn(name="vote_id")
	public List<VoteItem> getVoteItems() {
		return voteItems;
	}
	public void setVoteItems(List<VoteItem> voteItems) {
		this.voteItems = voteItems;
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
    @Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Basic
    @Column(name = "all_can_vote_number")
	public Integer getAllCanVoteNumber() {
		return allCanVoteNumber;
	}
	public void setAllCanVoteNumber(Integer allCanVoteNumber) {
		this.allCanVoteNumber = allCanVoteNumber;
	}
	
	@Basic
    @Column(name = "ecah_item_can_vote_number")
	public Integer getEachItemCanVoteNumber() {
		return eachItemCanVoteNumber;
	}
	public void setEachItemCanVoteNumber(Integer eachItemCanVoteNumber) {
		this.eachItemCanVoteNumber = eachItemCanVoteNumber;
	}

}
