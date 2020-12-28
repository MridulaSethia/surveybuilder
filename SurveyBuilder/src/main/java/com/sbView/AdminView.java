package com.sbView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sbController.AdminController;
import com.sbController.AdminControllerInterface;
import com.sbController.RespondentController;
import com.sbController.RespondentControllerInterface;
import com.sbController.SurveyorController;
import com.sbDao.AdminDAO;
import com.sbDao.AdminDAOInterface;
import com.sbDao.RespondentDAO;
import com.sbDao.RespondentDAOInterface;
import com.sbDao.SurveyorDAO;
import com.sbDao.SurveyorDAOInterface;
import com.sbEntity.Admin;
import com.sbEntity.Respondent;
import com.sbEntity.Surveyor;

public class AdminView {
	
	public void AdminMain(Admin c_user) throws Exception {
	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			SurveyorDAOInterface sdi = new SurveyorDAO();
			RespondentDAOInterface rdi = new RespondentDAO();
			AdminDAOInterface addi = new AdminDAO();
			AdminControllerInterface aci = new AdminController();
	
			Surveyor current_Surveyor = new Surveyor();
			current_Surveyor.setSurveyorId(c_user.getAdminId());
			current_Surveyor.setName(c_user.getName());
			current_Surveyor.setEmailId(c_user.getEmailId());
			current_Surveyor.setPassword(c_user.getPassword());
			
			Respondent current_Respondent = new Respondent();
			current_Respondent.setRespondentId(c_user.getAdminId());
			current_Respondent.setName(c_user.getName());
			current_Respondent.setEmailId(c_user.getEmailId());
			current_Respondent.setPassword(c_user.getPassword());
			
			SurveyorController sci = new SurveyorController();
			RespondentControllerInterface rci = new RespondentController();
			
			
			int ch = 0;
			do {
				try {
					System.out.println("\n*****************************MAIN MENU***************************");
					System.out.println("press 1 to view your profile");
					System.out.println("press 2 to edit your profile");
					System.out.println("press 3 to create new survey");
					System.out.println("press 4 to edit previous servey");
					System.out.println("press 5 to distribute survey");
					System.out.println("press 6 to stop distributed survey");
					System.out.println("press 7 to review survey");
					System.out.println("press 8 to view all Surveys");
					System.out.println("press 9 to view available surveys");
					System.out.println("press 10 to fill out servey");
					System.out.println("press 11 to log Out");
					
					System.out.println("\nenter your choice");
					String str = br.readLine();
					
					ch= Integer.parseInt(str);
					
					switch(ch) {
					case 1: 
						aci.viewCurrentProfile(c_user);
						break;
					case 2:
						aci.editCurrentProfile(c_user);
						break;
					case 3:
						aci.createSurvey(current_Surveyor);
						break;
					case 4:
						aci.editSurvey(current_Surveyor);
						break;
					case 5:
						aci.distributeSurvey(current_Surveyor);
						break;
					case 6:
						aci.stopDistributedSurvey(current_Surveyor);
						break;
					case 7:
						aci.reviewSurvey(current_Surveyor);
						break;
					case 8:
						aci.viewAllSurvey(current_Surveyor);
						break;
					case 9:
						aci.viewAvailableServeys(current_Respondent);
						break;
					case 10:
						aci.fillServey(current_Respondent);
					case 11:
						break;
					default:
						System.out.println("wrong choice");
					}
				}catch(Exception e) {
					System.out.println("Invalid inputs");
				}
			}while(ch != 11);
		
	}
	
}
