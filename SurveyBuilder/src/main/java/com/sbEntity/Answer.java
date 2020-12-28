package com.sbEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Answer")
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long aid;
	String ans;


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "QueId")
	private Question que = new Question();
	
	
	
	public Question getQue() {
		return que;
	}

	public void setQue(Question que) {
		this.que = que;
	}
	
	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	
	

}
