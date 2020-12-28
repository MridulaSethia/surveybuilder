package com.SurveyBuilder;

import com.sbDao.SurveyDAO;
import com.sbDao.SurveyDAOInterface;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

import junit.framework.TestCase;

public class TesySurveysDAO extends TestCase {
	
	SurveyDAOInterface surd;
	
	protected void setUp() throws Exception {
		super.setUp();
		surd = new SurveyDAO();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		surd = null;
	}

	public void testCreateSurvey() {
		Survey s = new Survey();
		s.setTitle("Demo Survey");
		s.setDueDate("22/01/2022");
		s.setStatus("Active");

		int i= surd.createSurvey(s);
		assert i>=0:("Not yet created");
	}

	public void testViewSurvey() {
		Survey s = new Survey();
		s.setsId(2);
		Survey sd =surd.viewSurvey(s);
		
		assert sd != null:("Record not found !!!");
		
	}

	public void testEditSurvey() {
		Survey s= new Survey();
		s.setsId(2);
		s.setTitle("Favourite Survey");
		s.setDueDate("26/01/2022");
		s.setStatus("Passive");
		int i = surd.editSurvey(s);
		
		assert i>=0 :("Survey not edited!!!");
	}

	public void testDeleteSurvey() {
		Survey s= new Survey();
		s.setsId(18);
		int i = surd.deleteSurvey(s);
		
		assert i>=0 :("Survey not deleted !!!");
		
	}

	public void testSearchSurvey()
	{
		Survey s= new Survey();
		s.setsId(19);
		int i = surd.deleteSurvey(s);
		
		assert i>=0 :("Survey not found !!!");
	}

	public void testViewAllSurveys() {
		Survey s= new Survey();
		//s.setSurveyor(surveyor);
	}

	public void testGetQuestionsBySid() {
		
	}

	public void testGetSurveysBySurveyorId() {
		fail("Not yet implemented");
	}

	public void testDistributeSurvey() {
		Survey s= new Survey();
		s.setsId(10);
		s.setStatus("Active");
		int i = surd.distributeSurvey(s);
		
		assert i>=0 :("Survey not distributed !!!");
	}

	public void testStopDistributedSurvey() {
		Survey s= new Survey();
		s.setsId(21);
		s.setStatus("Passive");
		int i = surd.distributeSurvey(s);
		
		assert i>=0 :("Survey not stopped !!!");
	}

}
