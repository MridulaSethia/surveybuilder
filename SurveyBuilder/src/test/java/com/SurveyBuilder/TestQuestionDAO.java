package com.SurveyBuilder;

import java.util.List;

import com.sbDao.QuestionDAO;
import com.sbDao.QuestionDAOInterface;
import com.sbEntity.Question;
import com.sbEntity.Survey;

import junit.framework.TestCase;

public class TestQuestionDAO extends TestCase {
	private QuestionDAOInterface qdi;
	protected void setUp() throws Exception {
		super.setUp();
		qdi = new QuestionDAO();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreateQuestion() {
		Question q= new Question();
		q.setQuestion("q1");
		q.setOption1("opt1");
		q.setOption2("opt2");
		q.setOption3("opt3");
		q.setOption4("opt4");
		int i = qdi.createQuestion(q);
		
		assert i >= 0 :("Question not saved !!!");
	}

	public void testViewQuestion() {
		Question q= new Question();
		q.setQid(13);
		Question i = qdi.viewQuestion(q);
		assert i != null :("Question not found !!!");
	}

	public void testEditQuestion() {
		Question q= new Question();
		q.setQid(13);
		q.setQuestion("q1");
		q.setOption1("opt1");
		q.setOption2("opt2");
		q.setOption3("opt3");
		q.setOption4("opt4");
		int i = qdi.editQuestion(q);
		assert i >= 0 :("Question not edited !!!");
	}

	public void testDeleteQuestion() {
		Question q= new Question();
		q.setQid(13);
		int i = qdi.deleteQuestion(q);
		assert i >= 0 :("Question not deleted");
	}

	public void testSearchQuestion() {
		Question q= new Question();
		q.setQid(13);
		int i = qdi.searchQuestion(q);
		assert i >= 0 :("Question not found");
	}

	public void testViewAllQuestions() {
		List<Question> i = qdi.viewAllQuestions();
		assert i != null:("Question not found");
	}

	public void testGetQuestionsBySid() {
		Survey s = new Survey();
		s.setSId(1);
		List<Question> i = qdi.getQuestionsBySid(s);
		assert i != null :("Question not found");
	}

}
