package com.sbDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.sbEntity.Respondent;
import com.sbEntity.Survey;
import com.sbEntity.Surveyor;

public class RespondentDAO implements RespondentDAOInterface{
	
			public void createRespondentProfile(Respondent r) {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
				 
			     EntityManager em = emf.createEntityManager();
			 
			     EntityTransaction t = em.getTransaction();
			     t.begin();
			     em.persist(r);
			     
			     t.commit();
			}

			public Respondent viewCurrentProfileDAO(Respondent cr) {
				
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		        EntityManager em = emf.createEntityManager();
		        
		        Respondent surveyor = em.find(Respondent.class, cr.getRespondentId());
		        em.close();
		        
		        return surveyor;
				
			}

			
			
			public void editCurrentProfileDAO(Respondent cr) {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
			    EntityManager em = emf.createEntityManager();
		        EntityTransaction t = em.getTransaction();
		        t.begin();
		        
		        Query q =  em.createQuery("update com.sbEntity.Respondent s set s.name =: nm, s.emailId =: eid, s.password =: pass where s.RespondentId =: rId");
		        q.setParameter("nm", cr.getName());
		        q.setParameter("eid", cr.getEmailId());
		        q.setParameter("pass", cr.getPassword());
		        q.setParameter("rId", cr.getRespondentId());
		        
		        q.executeUpdate();
		        
		        t.commit();
		        em.close();
			}

			


			public List<Respondent> viewAllRespondent() {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		        EntityManager em = emf.createEntityManager();

		        Query q = em.createQuery("from com.sbEntity.Respondent r");
		        List<Respondent> rl = q.getResultList();

		        em.close();
		        
		        return rl;
			}
			
			
			public Respondent respondentAuthenticationDAO(Respondent cu) {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		        EntityManager em = emf.createEntityManager();
		        
		        Query q = em.createQuery("from com.sbEntity.Respondent r where emailId =: semail and password =: spass");
		        q.setParameter("semail", cu.getEmailId());
		        q.setParameter("spass", cu.getPassword());
		        Respondent r = (Respondent) q.getSingleResult();

		        
		        em.close();
		        
		        return r;
			}

			
			
			public List<Survey> viewAvailableServeysDAO(Respondent cur_resp) {
				
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		        EntityManager em = emf.createEntityManager();

		        Query q = em.createQuery("from com.sbEntity.Survey s where s.status =: s");
		        q.setParameter("s", "Active");
		        List<Survey> rl = q.getResultList();
		        
		        em.close();
		        
		        return rl;
			}
			
			public void fillSurvey() {
				// TODO Auto-generated method stub
				
			}
		

}
