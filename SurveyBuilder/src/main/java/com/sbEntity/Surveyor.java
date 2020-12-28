package com.sbEntity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Surveyor")
public class Surveyor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long SurveyorId;
	private String name;
	private String emailId;
	private String password;
	
	@OneToMany(mappedBy = "surveyor", fetch = FetchType.LAZY, targetEntity = Survey.class, cascade = CascadeType.ALL)	
	private List<Survey> surveys;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getSurveyorId() {
		return SurveyorId;
	}
	public void setSurveyorId(long surveyorId) {
		SurveyorId = surveyorId;
	}
	public List<Survey> getSurveys() {
		return surveys;
	}
	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}
	
	@Override
	public String toString() {
		return "\nSurveyor Id : "+this.getSurveyorId()
		+"\nName : "+this.getName()
		+"\nEmail Id : "+this.getEmailId()
		+"\nPassword : "+this.getPassword()+"\n";
	}
	
	
}
