package com.SurveyBuilder;

import java.util.List;

import com.sbDao.RespondentDAO;
import com.sbDao.RespondentDAOInterface;
import com.sbDao.SurveyorDAO;
import com.sbDao.SurveyorDAOInterface;
import com.sbEntity.Respondent;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

import junit.framework.TestCase;

public class TestRespondentDAO extends TestCase {

	RespondentDAOInterface rdi;
	
	protected void setUp() throws Exception {
		super.setUp();
		rdi = new RespondentDAO();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreateRespondentProfile() {
		Respondent r = new Respondent();
		r.setName("demo");
		r.setEmailId("demo@gmail.com");
		r.setPassword("123");
		
		int i = rdi.createRespondentProfile(r);
		assert i > 0: "Failed";
	}

	public void testViewCurrentProfileDAO() {
		Respondent r = new Respondent();
		r.setName("demo");
		r.setEmailId("demo@gmail.com");
		r.setPassword("123");
		
		Respondent i = rdi.viewCurrentProfileDAO(r);
		assert i != null : "Failed";
	}

	public void testEditCurrentProfileDAO() {
		Respondent r = new Respondent();
		r.setName("demo");
		r.setEmailId("demo@gmail.com");
		r.setPassword("123");
		
		int i = rdi.editCurrentProfileDAO(r);
		assert i > 0 : "Failed";
	}

	public void testViewAllRespondent() {
		List<Respondent> rs = rdi.viewAllRespondent();
		assert rs != null : "Failed";
	}

	public void testRespondentAuthenticationDAO() {
		Respondent r = new Respondent();
		r.setEmailId("demo@gmail.com");
		r.setPassword("123");
		
		Respondent rs = rdi.respondentAuthenticationDAO(r);
		assert rs != null : "Failed";
	}

	public void testViewAvailableServeysDAO() {
		Respondent r = new Respondent();
		r.setName("demo");
		r.setEmailId("demo@gmail.com");
		r.setPassword("123");
		r.setRespondnetId(19);
		
		List<Survey> rs = rdi.viewAvailableServeysDAO(r);
		assert rs != null : "Failed";
	}

}
