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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sbDao.QuestionDAO;
import com.sbDao.SurveyDAO;

@Entity
@Table(name="survey")
public class Survey {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sId;
	
	private String title;
	private String dueDate;
	private String status;
	private float feedback;
	
	@OneToMany(mappedBy = "s", fetch = FetchType.LAZY, targetEntity = Question.class,  orphanRemoval = true, cascade = CascadeType.ALL)	
	private List<Question> questions = new ArrayList<Question>();
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SurveyorId")
	private Surveyor surveyor;
	
	 
	 @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	 @JoinTable(name = "Survey_Respondent", 
	 joinColumns = { @JoinColumn(name = "sId") }, 
	 inverseJoinColumns = { @JoinColumn(name = "respondentId") })
	 private List<Respondent> respondent = new ArrayList<Respondent>();
	 

	public long getsId() {
		return sId;
	}

	public void setsId(long sId) {
		this.sId = sId;
	}

	public List<Respondent> getRespondent() {
		return respondent;
	}

	public void setRespondent(List<Respondent> respondent) {
		this.respondent = respondent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Surveyor getSurveyor() {
		return surveyor;
	}

	public void setSurveyor(Surveyor surveyor) {
		this.surveyor = surveyor;
	}

	public String getTitle() {
		return title;
		
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	
	public long getSId() {
		return sId;
	}

	public void setSId(long sId) {
		this.sId = sId;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void setFeedback(float feedback) {
		this.feedback = feedback;
	}

	public float getFeedback() {
		return feedback;
	}
	
	@Override
	public String toString(){
		String msg = "\nSId: " 
		+ this.getSId() + "\nTitle: " 
		+ this.getTitle()+ "\nDue Date: "
		+ this.getDueDate()+ "\nFeedback: "
		+ this.getFeedback();
			
			
		SurveyDAO sdi = new SurveyDAO();
		this.setQuestions((ArrayList<Question>) sdi.getQuestionsBySid(this));
			
		List<Question> ques = (ArrayList<Question>)this.getQuestions();
		for(Question qtn: ques) {
			msg += qtn.toString();
		}	
			
		msg+="\n";
			
		return msg;
	}

}

