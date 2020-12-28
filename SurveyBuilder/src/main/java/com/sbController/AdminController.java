package com.sbController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sbDao.AdminDAO;
import com.sbDao.AdminDAOInterface;
import com.sbDao.AnswerDAO;
import com.sbDao.AnswerDAOInterface;
import com.sbDao.QuestionDAO;
import com.sbDao.QuestionDAOInterface;
import com.sbDao.RespondentDAO;
import com.sbDao.RespondentDAOInterface;
import com.sbDao.SurveyDAO;
import com.sbDao.SurveyDAOInterface;
import com.sbDao.SurveyorDAO;
import com.sbDao.SurveyorDAOInterface;
import com.sbEntity.Admin;
import com.sbEntity.Answer;
import com.sbEntity.Question;
import com.sbEntity.Respondent;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

public class AdminController implements AdminControllerInterface{
	
	private SurveyorDAOInterface sdi = new SurveyorDAO();
	private AdminDAOInterface addi = new AdminDAO();
	private RespondentDAOInterface rdi = new RespondentDAO();
	private SurveyDAOInterface surveyDao = new SurveyDAO();
	private QuestionDAOInterface questionDao = new QuestionDAO(); 
	private AnswerDAOInterface adi = new AnswerDAO();
	private QuestionDAOInterface qdi = new QuestionDAO();
	
	private static Logger logger = Logger.getLogger(AdminController.class);
	
	public void viewCurrentProfile(Admin cu) {
		Admin user = addi.viewCurrentProfileDAO(cu);
		if(user != null) {
			logger.info(user.toString());
			System.out.println(user.toString());
		}else {
			logger.error("No profile found...");
			System.out.println("No profile found...");
		}
	}

	
	public void editCurrentProfile(Admin cu){
		Admin updated_user = cu;
	
		try {
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
			
			if(addi.editCurrentProfileDAO(updated_user) == 1) {
				logger.info("Profile Updated Successfully");
				System.out.println("Profile Updated Successfully");
			}else {
				logger.error("Can't update profile");
				System.out.println("Can't update profile");
		}
		}catch(Exception e) {
			
			logger.error("Invalid Input...");
			System.out.println("Invalid Input...");
		}
	}
	
public void createSurvey(Surveyor cu){
		
		Survey s = new Survey();
		
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter Title of Servey");
			s.setTitle(br.readLine());
			
			System.out.println("Enter Due Date of Servey (DD/MM/YYYY)");
			s.setDueDate(br.readLine());
			
			for(Respondent r : rdi.viewAllRespondent()) {
				s.getRespondent().add(r);
			}
			
			s.setSurveyor(cu);
			s.setStatus("Passive");
			
			List<Question> queList = new ArrayList<Question>();
			
			char ch = 'y';
			do {
				Question q = new Question();
				
				System.out.println("Enter Question");
				q.setQuestion(br.readLine());
				
				System.out.println("Enter Option 1");
				q.setOption1(br.readLine());
				
				System.out.println("Enter Option 2");
				q.setOption2(br.readLine());
				
				System.out.println("Enter Option 3");
				q.setOption3(br.readLine());
				
				System.out.println("Enter Option 4");
				q.setOption4(br.readLine());
				
				q.setS(s);
				
				queList.add(q);
				s.getQuestions().add(q);
				
				System.out.println("Do you want to add another Question(Y/N)");
				ch = (br.readLine()).charAt(0);
							
			}while(ch == 'y' || ch == 'Y');
		
			if(surveyDao.createSurvey(s) == 1) {
				logger.info("new Survey created successfully...");
				System.out.println("new Survey created successfully...");
			}else {
				logger.error("Can't create Survey  :(");
				System.out.println("Can't create Survey  :(");
			}
		}catch(Exception e) {
			
			logger.error("Invalid Input...");
			System.out.println("Invalid Input...");
		}
		
	}

		
	public void editSurvey(Surveyor cu){
		Survey prevServey = new Survey();
		try {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter Survey ID to edit");
		
		prevServey.setSId(Long.parseLong(br.readLine()));
		
		Survey updatedSurvey = surveyDao.viewSurvey(prevServey);
		
		if(!updatedSurvey.getStatus().equals("Active")) {
			List<Question> qlist = (ArrayList<Question>) surveyDao.getQuestionsBySid(prevServey);
			updatedSurvey.setQuestions(qlist);
			
			System.out.println("press 1 to edit Title\n"+
					"press 2 to edit Due Date\n"+
					"press 3 to edit Question\n"+
					"press 4 to back");
			
			
			int ch = Integer.parseInt(br.readLine());
				
			switch(ch) {
			case 1:
				System.out.println("enter new Title");
				updatedSurvey.setTitle(br.readLine());
	
				break;
		
			case 2:
				System.out.println("enter new Due Date (DD/MM/YYYY)");
				updatedSurvey.setDueDate(br.readLine());
				
				break;
			
			case 3:{	
				System.out.println("press 1 to add Question\n"+
						"press 2 to delete Question\n"+
						"press 3 to edit Question\n"+
						"press 4 to view all questions of this servey\n"+
						"press 5 to back");
				
			
				int ch1 = Integer.parseInt(br.readLine());
				
				switch(ch1) {
				case 1:
					Question q = new Question();
					
					System.out.println("Enter Question");
					q.setQuestion(br.readLine());
					
					System.out.println("Enter Option 1");
					q.setOption1(br.readLine());
					
					System.out.println("Enter Option 2");
					q.setOption2(br.readLine());
					
					System.out.println("Enter Option 3");
					q.setOption3(br.readLine());
					
					System.out.println("Enter Option 4");
					q.setOption4(br.readLine());
					
					q.setS(updatedSurvey);
					qlist.add(q);
					questionDao.createQuestion(q);
					updatedSurvey.setQuestions(qlist);
					
					break;
			
				case 2:
					Question q1 = new Question();
					System.out.println("Enter Qid to delete Question");
					q1.setQid(Long.parseLong(br.readLine()));
					
					questionDao.deleteQuestion(q1);
					qlist.remove(q1);
					
					updatedSurvey.setQuestions(qlist);
					
					break;
				
				case 3:
					Question prevQ = new Question();
					System.out.println("Enter Qid to update Question");
					prevQ.setQid(Long.parseLong(br.readLine()));
					
					Question updatedQ = questionDao.viewQuestion(prevQ);
					
					
					System.out.println("press 1 to edit Question\n"+
					"press 2 to edit option1\n"+
					"press 3 to edit option2\n"+
					"press 4 to edit option3\n"+
					"press 5 to edit option4\n");
					
					int ch2 = Integer.parseInt(br.readLine());
						
					switch(ch2) {
					case 1:
						System.out.println("enter new Question");
						updatedQ.setQuestion(br.readLine());
						
						break;
					case 2:
						System.out.println("enter new Option1");
						updatedQ.setOption1(br.readLine());
						
						break;
					case 3:
						System.out.println("enter new Option2");
						updatedQ.setOption2(br.readLine());
						
						break;
					case 4:
						System.out.println("enter new Option3");
						updatedQ.setOption3(br.readLine());
						
						
						break;
					case 5:
						System.out.println("enter new Option4");
						updatedQ.setOption4(br.readLine());
				        
						break;
					default:
						System.out.println("Invalid choice");
					}
					
					questionDao.editQuestion(updatedQ);
					qlist.remove(prevQ);
					qlist.add(updatedQ);
					
					updatedSurvey.setQuestions(qlist);
					
					
					break;
				case 4:
					
					for(Question qtn: qlist) {
						System.out.println( qtn.toString());
					}
					
					break;
					
				default:
					System.out.println("Invalid choice");
				}
				
				break;
			}
			
			default:
				System.out.println("Invalid choice");
			}
			
			updatedSurvey.setSurveyor(cu);
			
			if(surveyDao.editSurvey(updatedSurvey) == 1) {
				logger.info("Survey edited successfully...");
				System.out.println("Survey edited successfully...");
			}else {
				logger.error("Can't Edit Survey  :(");
				System.out.println("Can't Edit Survey  :(");
			}
			
		}else {
			logger.error("Can not edit this Servey It is Destributed....");
			System.out.println("Can not edit this Servey It is Destributed....");
		}
		
		}catch(Exception e) {
			
			logger.error("Invalid Input");
			System.out.println("Invalid Input");
		}
	}

	
	public void distributeSurvey(Surveyor cu){
		try {
			Survey sv = new Survey();
			
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter Survey ID to Distribute ");
			sv.setSId(Long.parseLong(br.readLine()));
			
			if(surveyDao.distributeSurvey(sv) == 1) {
				logger.info("Servey is distributed...");
				System.out.println("Servey is distributed...");
			}else {
				logger.error("Failed to distribute survey..");
				System.out.println("Failed to distribute survey..");
			}
		}catch(Exception e) {
			
			logger.error("Invalid input");
			System.out.println("Invalid input");
		}
	}

	
	public void stopDistributedSurvey(Surveyor cu){
		try {
			Survey sv = new Survey();
			
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter Survey ID to Stop Distribution ");
			sv.setSId(Long.parseLong(br.readLine()));
			
			if(surveyDao.stopDistributedSurvey(sv) == 1) {
				logger.info("Survey distribution stopped...");
				System.out.println("Survey distribution stopped...");
			}else {
				logger.error("Failed to stop distribution of survey  :(");
				System.out.println("Failed to stop distribution of survey  :(");
			}
			
		}catch(Exception e) {
			
			logger.error("Invalid input");
			System.out.println("Invalid input");
		}
	}

	
	public void reviewSurvey(Surveyor cu){
		try {
			Survey s = new Survey();
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("enter Survey ID to review the survey");
			s.setSId(Long.parseLong(br.readLine()));
			
			Survey cur_survey = surveyDao.viewSurvey(s);
			if(cur_survey != null) {
				List<Question> qs = questionDao.getQuestionsBySid(cur_survey);
				if(qs != null) {
					System.out.println("***********************Survey Details***************************");
					System.out.println("Survey ID: "+cur_survey.getSId());
					System.out.println("Due Date: "+cur_survey.getDueDate());
					System.out.println("Status: "+cur_survey.getStatus());
					System.out.println("Title : "+cur_survey.getTitle());
					
					for(Question q : qs) {
						int opt1 = 0, opt2 = 0, opt3 = 0, opt4 = 0;
						
						List<Answer> ans = adi.getAnswersByQid(q);
						if(ans != null) {
							for(Answer a : ans) {
								
								if(a.getAns().equals("1")) 
									opt1 += 1;
								else if(a.getAns().equals("2")) 
									opt2 += 1;
								else if(a.getAns().equals("3")) 
									opt3 += 1;
								else if(a.getAns().equals("4")) 
									opt4 += 1;
								
							}
							
							System.out.println(q.getQid()+". "+q.getQuestion());
							System.out.println("1) "+q.getOption1()+" :"+opt1);
							System.out.println("2) "+q.getOption2()+" :"+opt2);
							System.out.println("3) "+q.getOption3()+" :"+opt3);
							System.out.println("4) "+q.getOption4()+" :"+opt4+"\n");
						}else {
							logger.error("No Answers found...");
							System.out.println("No Answers found...");
						}
					}
				}else {
					logger.error("No Questions found");
					System.out.println("No Questions found");
				}
			}else {
				logger.error("No Servey found of this ID");
				System.out.println("No Servey found of this ID");
			}
			
		}catch(Exception e) {
			logger.error("Invalid input");
			System.out.println("Invalid input");
		}
	}

	
	public void viewAllSurvey(Surveyor cu) {
		List<Survey> surveys = (ArrayList<Survey>)surveyDao.getSurveysBySurveyorId(cu);
		if(surveys != null) {
			for(Survey s : surveys) {
				logger.info(s.toString());
				System.out.println(s.toString());
			}
		}else {
			logger.error("No Survey found");
			System.out.println("No Survey found");
		}
	}	

	
	public void viewAvailableServeys(Respondent cur_resp) {
		List<Survey> availables =  (ArrayList<Survey>) rdi.viewAvailableServeysDAO(cur_resp);
		if(availables != null) {
			for(Survey s : availables) {
				logger.info(s.getsId()+" : "+s.getTitle());
				System.out.println(s.getsId()+" : "+s.getTitle());
			}
		}
		else{
			logger.error("No serveys found....");
			System.out.println("No serveys found....");
		}
		
	}

	public void fillServey(Respondent cur_resp){
		Survey s = new Survey();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("enter Survey ID to fill the survey");
			s.setSId(Long.parseLong(br.readLine()));
			
			Survey cur_survey = surveyDao.viewSurvey(s);
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
			
			if(surveyDao.editSurvey(cur_survey) == 1) {
				logger.info("Thank you for feedback your responce is recorded :)");
				System.out.println("Thank you for feedback your responce is recorded :)");
			}else {
				logger.error("can't save feedback :(");
				System.out.println("can't save feedback :(");
			}
		}catch(Exception e) {
			logger.error("Invalid Input");
			System.out.println("Invalid Input");
		}
	}


}
