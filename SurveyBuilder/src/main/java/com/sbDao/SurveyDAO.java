package com.sbDao;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sbEntity.Question;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

public class SurveyDAO implements SurveyDAOInterface{

	public int createSurvey(Survey s) {
		
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		 
	     EntityManager em = emf.createEntityManager();
	 
	     EntityTransaction t = em.getTransaction();
	     t.begin();
	     em.merge(s);
	     
	     t.commit();
		
	     return 0;
	}
	
	

	public Survey viewSurvey(Survey s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        
        Survey su = em.find(Survey.class, s.getSId());
        
        em.close();
        
        return su;
	}

	
	
	public int editSurvey(Survey s) throws Exception{
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
	    EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        
        Query q =  em.createQuery("update com.sbEntity.Survey s set s.title =: tit, s.dueDate =: due, s.feedback =: feed where s.sId =: SId");
        q.setParameter("tit", s.getTitle());
        q.setParameter("due", s.getDueDate());
        q.setParameter("feed", s.getFeedback());
       
        q.setParameter("SId", s.getSId());
        
        q.executeUpdate();
        
        t.commit();
        em.close();
        	
        return i;

	}

	public int deleteSurvey(Survey s) {
		int i = 0;
		
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		 
		  EntityManager em1 = emf.createEntityManager();
		  EntityTransaction t1 = em1.getTransaction();
		  
		     t1.begin();
		       
		     Query query1 = em1.createQuery("delete from com.sbEntity.Question q where SurveyId =: sid " );
		     i += query1.setParameter("sid", s.getSId()).executeUpdate();
		     
		     t1.commit();
		     em1.close();
		 
		 
	     EntityManager em = emf.createEntityManager();
	     EntityTransaction t = em.getTransaction();
	               
	        
	     t.begin(); 
	     Query query = em.createQuery("delete from com.sbEntity.Survey s where s.sId =: sid  " );
	     i = query.setParameter("sid", s.getSId()).executeUpdate();
	     
	     t.commit();
	     em.close();
	     
	   
	     try { 
		}catch(Exception e) {
			i = 0;
			System.out.println("Something went wrong....");
		}
	     return i;
	}

	public int searchSurvey(Survey s) {
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        
        Survey su = em.find(Survey.class, s.getSId());
        
        em.close();
        if(su != null) {
        	i = 1;
        }
        
        return i;
	}

	public List<Survey> viewAllSurveys() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("from com.sbEntity.Survey s");
        List<Survey> su = q.getResultList();

        em.close();
        
        return su;
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
	
	public List<Survey> getSurveysBySurveyorId(Surveyor s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("from com.sbEntity.Survey s where SurveyorId =: sid");
        q.setParameter("sid", s.getSurveyorId());
        List<Survey> ql = q.getResultList();

        em.close();
        
        return ql;
	}



	public int distributeSurvey(Survey s) {
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
	    EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        
        Query q =  em.createQuery("update com.sbEntity.Survey s set s.status =: state where s.sId =: SId");
        q.setParameter("state", "Active");
        q.setParameter("SId", s.getSId());
        
        q.executeUpdate();
        
        t.commit();
        em.close();
        	
        return i;

	}
	
	public int stopDistributedSurvey(Survey s) {
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
	    EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        
        Query q =  em.createQuery("update com.sbEntity.Survey s set s.status =: state where s.sId =: SId");
        q.setParameter("state", "Passive");
        q.setParameter("SId", s.getSId());
        
        q.executeUpdate();
        
        t.commit();
        em.close();
        	
        return i;

	}

}
