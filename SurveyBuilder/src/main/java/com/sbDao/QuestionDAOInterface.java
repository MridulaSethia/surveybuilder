package com.sbDao;

import java.io.IOException;
import java.util.List;

import com.sbEntity.Question;
import com.sbEntity.Survey;

public interface QuestionDAOInterface {
	
	public int createQuestion(Question q);
	public Question viewQuestion(Question q);
	public int editQuestion(Question q) throws NumberFormatException, IOException;
	public int deleteQuestion(Question q);
	public int searchQuestion(Question q);
	public List<Question> viewAllQuestions();
	public List<Question> getQuestionsBySid(Survey s);

}
