package com.sbEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="question")
public class Question {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Qid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SurveyId")
	private Survey s;
	
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	@OneToMany(mappedBy = "que", fetch = FetchType.LAZY, targetEntity = Answer.class,  orphanRemoval = true, cascade = CascadeType.ALL)	
	private List<Answer> answers = new ArrayList<Answer>();
	
	
	
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public long getQid() {
		return Qid;
	}

	public void setQid(long qid) {
		Qid = qid;
	}

	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getQuestion() {
		return question;
		
	}
	
	public Survey getS() {
		return s;
	}

	public void setS(Survey s) {
		this.s = s;
	} 

	
	public String getOption1() {
		return option1;
		
	}
	
	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
		
		
	} 
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
		
		
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
		
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	
	@Override
	public String toString() {
		return "\nQid : "+this.getQid()
		+"\nQuestion : "+this.getQuestion()
		+"\nOption1: "+this.getOption1()
		+"\nOption2: "+this.getOption2()
		+"\nOption3: "+this.getOption3()
		+"\nOption4: "+this.getOption4()+"\n";
	}
}
