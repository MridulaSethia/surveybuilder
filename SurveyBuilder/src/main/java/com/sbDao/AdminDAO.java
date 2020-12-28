package com.sbDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sbEntity.Admin;

public class AdminDAO implements AdminDAOInterface{
	
	public int createAdminProfile(Admin r) {
		int i = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		EntityManager em = emf.createEntityManager();
		try {	 
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(r);
				
			i = 1;
			t.commit();
		}catch(Exception e) {
			
			i = 0;
		}finally {
			
			em.close();
		}
		return i;
	}

	public Admin viewCurrentProfileDAO(Admin cr) {
				
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		EntityManager em = emf.createEntityManager();
		Admin admin = null;
		try {  
			
			admin = em.find(Admin.class, cr.getAdminId());
		
		}catch(Exception e) {
			
			admin = null;
		}finally {
			
			em.close();
		}
		return admin;
				
	}

					
	public int editCurrentProfileDAO(Admin cr) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder"); 
		EntityManager em = emf.createEntityManager();
		int i = 0;
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			        
			Query q =  em.createQuery("update com.sbEntity.Admin s set s.name =: nm, s.emailId =: eid, s.password =: pass where s.AdminId =: rId");
			q.setParameter("nm", cr.getName());
			q.setParameter("eid", cr.getEmailId());
			q.setParameter("pass", cr.getPassword());
			q.setParameter("rId", cr.getAdminId());
			        
			q.executeUpdate();
			i = 1;    
			t.commit();
		}catch(Exception e) {
			i = 0;
		}finally {
			em.close();
		}
		return i;
	}

	
	public List<Admin> viewAllAdmin() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		EntityManager em = emf.createEntityManager();
		List<Admin> rl = null;
		try {
			
			Query q = em.createQuery("from com.sbEntity.Admin r");
			rl = q.getResultList();
		}catch(Exception e) {
			
			rl = null;
		}finally {
			
			em.close();
		}       
		return rl;
	}
					
	
	public Admin adminAuthenticationDAO(Admin cu) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurveyBuilder");
		EntityManager em = emf.createEntityManager();
		Admin r = null;
		try {
			
			Query q = em.createQuery("from com.sbEntity.Admin r where emailId =: semail and password =: spass");
			q.setParameter("semail", cu.getEmailId());
			q.setParameter("spass", cu.getPassword());
			r = (Admin) q.getSingleResult();
		}catch(Exception e){
			
			r = null;
		}finally {
			
			em.close();
		}        
		return r;
	}

	
			


}
