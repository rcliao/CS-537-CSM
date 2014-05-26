package org.csm.models;

import org.csm.models.dao.UserDao;
import org.csm.models.dao.jdbc.UserDaoImpl;
import java.sql.SQLException;

import junit.framework.Assert;

import org.csm.models.dao.UserDao;
import org.csm.models.dao.jdbc.UserDaoImpl;
import org.junit.Test;

import java.sql.SQLException;

public class UserTest {
	@Test
	public void testUsername() {
		// create a dummy user to test the assert function
		UserDao userdao = new UserDaoImpl();
		User u = null;
		try{
			u = userdao.getUser("ssaeedi", "student123");
		}catch(SQLException e){}

		assert u != null;
		assert u.getUsername().equals("ssaeedi");
		assert u.getPassword().equals("student123");
		assert u.getFirstName().equals("Saman");
		assert u.getLastName().equals("Saeedi");
		assert u.getEmail().equalsIgnoreCase("ssaeedi@calstatela.edu");
		assert u.getCin() == 555666777;
	}

	@Test
	public void testLogin() {
		UserDao dao = new UserDaoImpl();

		try
		{
			// call methods that might throw SQLException
			Assert.assertEquals(dao.getUser("eliao", "student123").getUsername(), "eliao");
		}
		catch (SQLException e)
		{
			// do something appropriate with the exception, *at least*:
			e.printStackTrace();
		}
	}
}