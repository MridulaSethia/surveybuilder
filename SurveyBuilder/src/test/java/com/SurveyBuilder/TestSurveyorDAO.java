package com.SurveyBuilder;

import com.sbDao.SurveyDAO;
import com.sbDao.SurveyDAOInterface;
import com.sbDao.SurveyorDAO;
import com.sbDao.SurveyorDAOInterface;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

import junit.framework.TestCase;

public class TestSurveyorDAO extends TestCase {

	SurveyorDAOInterface sdi;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		sdi = new SurveyorDAO();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		sdi = null;
		
	}

	public void testCreateSurveyorProfile() {
		Surveyor s = new Surveyor();
		s.setName("demo");
		s.setEmailId("demo@gmail.com");
		s.setPassword("123");
		
		int i = sdi.createSurveyorProfile(s);
		assert i > 0: "Failed";
	}

	public void testViewCurrentProfileDAO() {
		Surveyor cu = new Surveyor();
		cu.setSurveyorId(1);
		Surveyor s =sdi.viewCurrentProfileDAO(cu);
		
		assert s != null:("Record not found !!!");
		}

	public void testSurveyorAuthenticationDAO() {
		Surveyor cu = new Surveyor();
		cu.setEmailId("mehak@gmail.in");
		cu.setPassword("meh");
		Surveyor s = sdi.surveyorAuthenticationDAO(cu);
		
		assert s != null:("Authentication failed!!!");

	}

	public void testEditCurrentProfileDAO() {
		Surveyor cu = new Surveyor();
		cu.setSurveyorId(1);
		cu.setName("mehak sethia");
		cu.setEmailId("mehak@gmail.com");
		cu.setPassword("mehh");
		sdi.editCurrentProfileDAO(cu);
		
	}

	

}
