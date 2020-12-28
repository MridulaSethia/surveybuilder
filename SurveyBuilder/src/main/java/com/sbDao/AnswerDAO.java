package com.sbDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sbEntity.Answer;
import com.sbEntity.Question;

public class AnswerDAO implements AnswerDAOInterface{

	public int saveAnswer(Answer a) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
	    EntityManager em = emf.createEntityManager();
	     
	    EntityTransaction t = em.getTransaction();
	    t.begin();

	    em.merge(a);

	    t.commit();
	     
	    em.close();
	    return 0;
	}

	public List<Answer> getAnswersByQid(Question q) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();

        Query q1 = em.createQuery("from com.sbEntity.Answer a where queId =: qid");
        q1.setParameter("qid", q.getQid());
        List<Answer> ql = q1.getResultList();

        em.close();
        
        return ql;
	}
	
}
