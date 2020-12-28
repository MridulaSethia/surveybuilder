package com.sbController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.sbDao.AnswerDAO;
import com.sbDao.AnswerDAOInterface;
import com.sbDao.QuestionDAO;
import com.sbDao.QuestionDAOInterface;
import com.sbDao.RespondentDAO;
import com.sbDao.RespondentDAOInterface;
import com.sbDao.SurveyDAO;
import com.sbDao.SurveyDAOInterface;
import com.sbEntity.Answer;
import com.sbEntity.Question;
import com.sbEntity.Respondent;
import com.sbEntity.Survey;
import com.sbEntity.Respondent;

public class RespondentController implements RespondentControllerInterface{
	private RespondentDAOInterface rdi = new RespondentDAO();
	private SurveyDAOInterface sdi = new SurveyDAO();
	private QuestionDAOInterface qdi = new QuestionDAO();
	private AnswerDAOInterface adi = new AnswerDAO();
	
	public void viewCurrentProfile(Respondent cur_resp) {
		Respondent user = rdi.viewCurrentProfileDAO(cur_resp);
		
		System.out.println(user.toString());
	}

	public void editCurrentProfile(Respondent cur_resp) throws Exception {
		Respondent updated_user = cur_resp;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("press 1 to edit Name\n"+
		"press 2 to edit Email ID\n"+
		"press 3 to edit Password\n"+
		"press 4 to Back\n");
		
		int ch = Integer.parseInt(br.readLine());
			
		switch(ch) {
		case 1:
			System.out.println("enter new Name");
			updated_user.setName(br.readLine());
			break;
		case 2:
			System.out.println("enter new Email");
			updated_user.setEmailId(br.readLine());

			break;
		case 3:
			System.out.println("enter new Password");
			updated_user.setPassword(br.readLine());
	        
			break;
		case 4:
			break;
			
		default:
			System.out.println("Invalid choice");
		}
		
		rdi.editCurrentProfileDAO(updated_user);
	}

	public void viewAvailableServeys(Respondent cur_resp) {
		List<Survey> availables =  (ArrayList<Survey>) rdi.viewAvailableServeysDAO(cur_resp);; 
		for(Survey s : availables) {
			System.out.println(s.getsId()+" : "+s.getTitle());
		}
		
	}

	public void fillServey(Respondent cur_resp) throws Exception{
		Survey s = new Survey();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("enter Survey ID to fill the survey");
		s.setSId(Long.parseLong(br.readLine()));
		
		Survey cur_survey = sdi.viewSurvey(s);
		List<Question> qs = qdi.getQuestionsBySid(cur_survey);
		
		System.out.println("***********************Survey Form***************************");
		System.out.println("Survey ID: "+cur_survey.getSId());
		System.out.println("Due Date: "+cur_survey.getDueDate());
		System.out.println("Status: "+cur_survey.getStatus());
		System.out.println("Title : "+cur_survey.getTitle());
		for(Question q : qs) {
			System.out.println(q.getQid()+". "+q.getQuestion());
			System.out.println("1) "+q.getOption1());
			System.out.println("2) "+q.getOption2());
			System.out.println("3) "+q.getOption3());
			System.out.println("4) "+q.getOption4()+"\n");
			
			Answer ans = new Answer();
			System.out.print("Enter your responce: ");
			ans.setAns(br.readLine());
			ans.setQue(q);
			adi.saveAnswer(ans);
			
			System.out.println("\n");
	
		}
		
		System.out.print("Enter your over all Rating for this Survey (1.0 to 5.0) ");
		float fb = Float.parseFloat(br.readLine());
		cur_survey.setFeedback((cur_survey.getFeedback() + fb) / 2);
		sdi.editSurvey(cur_survey);
	}

}
