package com.sbView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.sbController.SurveyorController;
import com.sbDao.SurveyorDAO;
import com.sbDao.SurveyorDAOInterface;
import com.sbEntity.Surveyor;

public class SurveyorView {
	
	public static void SurveyorMain(Surveyor c_user) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SurveyorDAOInterface sdi = new SurveyorDAO();

		Surveyor current_user = sdi.viewCurrentProfileDAO(c_user);
		
		SurveyorController sci = new SurveyorController();
		
		int ch;
		do {
			System.out.println("\n*****************************MAIN MENU********************");
			System.out.println("press 1 to view your profile");
			System.out.println("press 2 to edit your profile");
			System.out.println("press 3 to create new survey");
			System.out.println("press 4 to edit previous servey");
			System.out.println("press 5 to distribute survey");
			System.out.println("press 6 to stop distributed survey");
			System.out.println("press 7 to review survey");
			System.out.println("press 8 to view all Surveys");
			System.out.println("press 9 to log Out");
			
			System.out.println("\nenter your choice");
			String str = br.readLine();
			
			ch= Integer.parseInt(str);
			
			switch(ch) {
			case 1: 
				sci.viewCurrentProfile(current_user);
				break;
			case 2:
				sci.editCurrentProfile(current_user);
				break;
			case 3:
				sci.createSurvey(current_user);
				break;
			case 4:
				sci.editSurvey(current_user);
				break;
			case 5:
				sci.distributeSurvey(current_user);
				break;
			case 6:
				sci.stopDistributedSurvey(current_user);
				break;
			case 7:
				sci.reviewSurvey(current_user);
				break;
			case 8:
				sci.viewAllSurvey(current_user);
				break;
			case 9:
				System.exit(0);
			default:
				System.out.println("wrong choice");
			}
		}while(ch != 9);
	}
}
