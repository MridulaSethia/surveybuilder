package com.SurveyBuilder;

import java.util.List;

import com.sbDao.AnswerDAO;
import com.sbDao.AnswerDAOInterface;
import com.sbEntity.Answer;
import com.sbEntity.Question;

import junit.framework.TestCase;

public class TestAnswerDAO extends TestCase {
	private AnswerDAOInterface adi;
	protected void setUp() throws Exception {
		super.setUp();
		adi = new AnswerDAO();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSaveAnswer() {
		Answer a = new Answer();
		a.setAns("1");
				
		int i = adi.saveAnswer(a);
		assert i > 0:("Failed to save");
	}

	public void testGetAnswersByQid() {
		Question q = new Question();
		q.setQid(14);
		List<Answer> i = adi.getAnswersByQid(q);
		assert i != null : ("No answer found for this question");
	}

}
