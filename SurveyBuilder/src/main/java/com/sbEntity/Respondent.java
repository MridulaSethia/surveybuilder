package com.sbEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sbEntity.Survey;

@Entity
@Table(name = "Respondent")
public class Respondent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long respondentId;
	private String name;
	private String emailId;
	private String password;
	
	
	@ManyToMany(mappedBy="respondent",cascade = CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Survey.class)
	private List<Survey> surveys = new ArrayList<Survey>();
	 
	
	public void setRespondentId(long respondentId) {
		this.respondentId = respondentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String eId) {
		this.emailId = eId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getRespondentId() {
		return respondentId;
	}
	public void setRespondnetId(long respId) {
		this.respondentId = respId;
	}
	public List<Survey> getSurveys() {
		return surveys;
	}
	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}
	
	
}
