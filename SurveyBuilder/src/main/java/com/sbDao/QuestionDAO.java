package com.sbDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


import com.sbEntity.Question;
import com.sbEntity.Survey;

public class QuestionDAO implements QuestionDAOInterface{

	public int createQuestion(Question q) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		    EntityManager em = emf.createEntityManager();
		     
		    EntityTransaction t = em.getTransaction();
		    t.begin();
	
		    em.merge(q);
	
		    t.commit();
		     
		    em.close();
		     
		 	
		}catch(Exception e) {
			
		}
		return 0;
	}
	
	

	public Question viewQuestion(Question q) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        
        Question que = em.find(Question.class, q.getQid());
        
        em.close();
        
        return que;
	}

	
	
	public int editQuestion(Question q) throws NumberFormatException, IOException {
	
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
			    EntityManager em = emf.createEntityManager();
		        EntityTransaction t = em.getTransaction();
		        t.begin();
		        
		        Query q0 =  em.createQuery("update com.sbEntity.Question q set q.question =: quest, q.option1 =: opt1, q.option2 =: opt2, q.option3 =: opt3, q.option4 =: opt4 where q.Qid =: QId");
		        q0.setParameter("quest", q.getQuestion());
		        q0.setParameter("opt1", q.getOption1());
		        q0.setParameter("opt2", q.getOption2());
		        q0.setParameter("opt3", q.getOption3());
		        q0.setParameter("opt4", q.getOption4());
		        q0.setParameter("QId", q.getQid());
		        
		        int i = q0.executeUpdate();
		        
		        t.commit();
		        em.close();
		        
		
		        return i;
	
	}
	
	

	public int deleteQuestion(Question q) {
		int i = 0;
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
	     EntityManager em = emf.createEntityManager();
	        
	     EntityTransaction t = em.getTransaction();
	               
	        
	     t.begin();
	       
	     Query query = em.createQuery("delete from com.sbEntity.Question q where q.Qid =: QId");
	     i = query.setParameter("QId", q.getQid()).executeUpdate();
	     
	     t.commit();
	     em.close();
	     
	     return i;
	}

	
	
	public int searchQuestion(Question q) {
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        
        Question que = em.find(Question.class, q.getQid());
        
        em.close();
        if(que != null) {
        	i = 1;
        }
        
        return i;
	}

	
	
	public List<Question> viewAllQuestions() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("from com.sbEntity.Question que");
        List<Question> ql = q.getResultList();

        em.close();
        
        return ql;

	}



	public List<Question> getQuestionsBySid(Survey s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("from com.sbEntity.Question que where SurveyId =: sid");
        q.setParameter("sid", s.getSId());
        List<Question> ql = q.getResultList();

        em.close();
        
        return ql;
	}

}
