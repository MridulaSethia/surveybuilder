package com.sbController;

import com.sbEntity.Admin;
import com.sbEntity.Respondent;
import com.sbEntity.Surveyor;

public interface AdminControllerInterface {
	
	public void viewCurrentProfile(Admin cu);
	public void editCurrentProfile(Admin cu);
	public void createSurvey(Surveyor cu);
	public void editSurvey(Surveyor cu);
	public void distributeSurvey(Surveyor cu);
	public void stopDistributedSurvey(Surveyor cu);
	public void reviewSurvey(Surveyor cu);
	public void viewAllSurvey(Surveyor cu);
	void viewAvailableServeys(Respondent cur_resp);
	void fillServey(Respondent cur_resp);

}
