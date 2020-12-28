package com.sbDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sbEntity.Question;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

public class SurveyorDAO implements SurveyorDAOInterface{
	
	public void createSurveyorProfile(Surveyor s) {
 EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		 
	     EntityManager em = emf.createEntityManager();
	 
	     EntityTransaction t = em.getTransaction();
	     t.begin();
	     em.persist(s);
	     
	     t.commit();
	}

	public Surveyor viewCurrentProfileDAO(Surveyor cu) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        
        Surveyor surveyor = em.find(Surveyor.class, cu.getSurveyorId());
        em.close();
        
        return surveyor;
		
	}
	
	public Surveyor surveyorAuthenticationDAO(Surveyor cu) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
        EntityManager em = emf.createEntityManager();
        
        Query q = em.createQuery("from com.sbEntity.Surveyor s where emailId =: semail and password =: spass");
        q.setParameter("semail", cu.getEmailId());
        q.setParameter("spass", cu.getPassword());
        Surveyor s = (Surveyor) q.getSingleResult();

        
        em.close();
        
        return s;
		
	}

	
	
	public void editCurrentProfileDAO(Surveyor cu) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
	    EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        
        Query q =  em.createQuery("update com.sbEntity.Surveyor s set s.name =: nm, s.emailId =: eid, s.password =: pass where s.SurveyorId =: SuId");
        q.setParameter("nm", cu.getName());
        q.setParameter("eid", cu.getEmailId());
        q.setParameter("pass", cu.getPassword());
        q.setParameter("SuId", cu.getSurveyorId());
        
        q.executeUpdate();
        
        t.commit();
        em.close();
	}

	public void createSurveyDAO(Surveyor cu) {
		// TODO Auto-generated method stub
		
	}

	public void editSurveyDAO(Surveyor cu) {
		// TODO Auto-generated method stub
		
	}

	public void distributeSurveyDAO(Surveyor cu) {
		// TODO Auto-generated method stub
		
	}

	public void viewRespondentsDAO(Surveyor cu) {
		// TODO Auto-generated method stub
		
	}

	public void viewPendingSurveyDAO(Surveyor cu) {
		// TODO Auto-generated method stub
		
	}

	public void viewAllSurveyDAO(Surveyor cu) {
		// TODO Auto-generated method stub
		
	}



}
