package com.sbDao;

import java.util.List;

import com.sbEntity.Admin;

public interface AdminDAOInterface {
	public int createAdminProfile(Admin r);
	public Admin viewCurrentProfileDAO(Admin cr);
	public int editCurrentProfileDAO(Admin cr);
	public Admin adminAuthenticationDAO(Admin cu);
	public List<Admin> viewAllAdmin();
	
}
