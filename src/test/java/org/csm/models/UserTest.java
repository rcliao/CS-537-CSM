package org.csm.models;

import org.csm.models.dao.UserDao;
import org.csm.models.dao.jdbc.UserDaoImpl;

import junit.framework.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class UserTest {
	@Test
	public void testUsername() {
		// create a dummy user to test the assert function
		User testUser = new User("eliao");

		Assert.assertEquals(testUser.getUsername(), "eliao");
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