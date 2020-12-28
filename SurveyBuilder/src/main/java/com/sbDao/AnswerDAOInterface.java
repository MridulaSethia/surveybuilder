package com.sbDao;

import java.util.List;

import com.sbEntity.Answer;
import com.sbEntity.Question;

public interface AnswerDAOInterface {
	public int saveAnswer(Answer a);
	public List<Answer> getAnswersByQid(Question q);
}
