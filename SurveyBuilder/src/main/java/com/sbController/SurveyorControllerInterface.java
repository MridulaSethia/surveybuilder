package com.sbController;

import com.sbEntity.Surveyor;

public interface SurveyorControllerInterface {
	public void viewCurrentProfile(Surveyor cu);
	public void editCurrentProfile(Surveyor cu) throws Exception;
	public void createSurvey(Surveyor cu) throws Exception;
	public void editSurvey(Surveyor cu) throws Exception;
	public void distributeSurvey(Surveyor cu) throws Exception;
	public void stopDistributedSurvey(Surveyor cu) throws Exception;
	public void reviewSurvey(Surveyor cu) throws Exception;
	public void viewAllSurvey(Surveyor cu);
}
