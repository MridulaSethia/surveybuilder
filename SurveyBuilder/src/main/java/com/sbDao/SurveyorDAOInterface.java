package com.sbDao;

import com.sbEntity.Surveyor;

public interface SurveyorDAOInterface {
	
		public void createSurveyorProfile(Surveyor s);
		public Surveyor viewCurrentProfileDAO(Surveyor cu);
		public void editCurrentProfileDAO(Surveyor cu);
		public void createSurveyDAO(Surveyor cu);
		public void editSurveyDAO(Surveyor cu);
		public void distributeSurveyDAO(Surveyor cu);
		public void viewRespondentsDAO(Surveyor cu);
		public void viewPendingSurveyDAO(Surveyor cu);
		public void viewAllSurveyDAO(Surveyor cu);
		public Surveyor surveyorAuthenticationDAO(Surveyor cu);
}
