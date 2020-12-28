package com.sbView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sbController.RespondentController;
import com.sbController.RespondentControllerInterface;
import com.sbDao.RespondentDAO;
import com.sbDao.RespondentDAOInterface;
import com.sbDao.SurveyorDAO;
import com.sbDao.SurveyorDAOInterface;
import com.sbEntity.Respondent;
import com.sbEntity.Surveyor;

public class RespondentView {

	public static void RespondentMain(Respondent cur_resp) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		 RespondentDAOInterface rdi = new RespondentDAO();

		 Respondent current_respondent = rdi.viewCurrentProfileDAO(cur_resp);
		 
		 
		 int ch;
			do {
				System.out.println("\n*****************************MAIN MENU********************");
				System.out.println("press 1 to view your profile");
				System.out.println("press 2 to edit your profile");
				System.out.println("press 3 to view available surveys");
				System.out.println("press 4 to fill out servey");
				System.out.println("press 5 to log Out");
				
				System.out.println("\nenter your choice");
				String str = br.readLine();
				
				ch= Integer.parseInt(str);
				
				RespondentControllerInterface rci = new RespondentController();
					
				switch(ch) {
					case 1: 
						rci.viewCurrentProfile(cur_resp);
						break;
					case 2:
						rci.editCurrentProfile(cur_resp);
						break;
					case 3:
						rci.viewAvailableServeys(cur_resp);
						break;
					case 4:
						rci.fillServey(cur_resp);
						break;
					case 5:
						break;
				}
				
			}while(ch != 5);
	}

}
