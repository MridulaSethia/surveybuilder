package com.SurveyBuilder;

import java.util.List;

import com.sbDao.SurveyDAO;
import com.sbDao.SurveyDAOInterface;
import com.sbEntity.Question;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

import junit.framework.TestCase;

public class TestSurveyDAO extends TestCase {

	private SurveyDAOInterface surd;
	
	protected void setUp() throws Exception {
		super.setUp();
		surd = new SurveyDAO();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
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
		
		assert i >= 0 :("Survey not found !!!");
	}

	public void testViewAllSurveys() {
		
		List<Survey>  s = surd.viewAllSurveys();
		 assert s != null : ("No Serveys found");
	}

	public void testGetQuestionsBySid() {
		Survey s= new Survey();
		s.setsId(19);
		List<Question>  q = surd.getQuestionsBySid(s);
		
		assert q != null : ("No Question found");
	}

	public void testGetSurveysBySurveyorId() {
		Surveyor s= new Surveyor();
		s.setSurveyorId(1);
		List<Survey>  su = surd.getSurveysBySurveyorId(s);
		
		assert su != null : ("No Servey found");
	}

	public void testDistributeSurvey() {
		Survey s= new Survey();
		s.setsId(21);
		s.setStatus("Passive");
		int i = surd.stopDistributedSurvey(s);
		
		assert i>=0 :("Survey distribution not stopped !!!");
	}

	public void testStopDistributedSurvey() {
		Survey s= new Survey();
		s.setsId(21);
		s.setStatus("Passive");
		int i = surd.distributeSurvey(s);
		
		assert i>=0 :("Survey is not distributed !!!");
	}

}
