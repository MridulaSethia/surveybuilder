package com.sbDao;

import java.util.List;

import com.sbEntity.Question;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

public interface SurveyDAOInterface {
	
	public int createSurvey(Survey s);
	public Survey viewSurvey(Survey s);
	public int editSurvey(Survey s) throws Exception;
	public int deleteSurvey(Survey s);
	public int searchSurvey(Survey s);
	public int distributeSurvey(Survey s);
	public int stopDistributedSurvey(Survey s);
	public List<Survey> viewAllSurveys();
	public List<Question> getQuestionsBySid(Survey s);
	public List<Survey> getSurveysBySurveyorId(Surveyor s);
	
}
