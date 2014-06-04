package org.csm.models;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.csm.models.dao.EnrollmentDao;
import org.csm.models.dao.jdbc.EnrollmentDaoImpl;
import org.csm.models.dao.jdbc.ScheduleDaoImpl;
import org.junit.Test;

public class EnrollmentTest {
	

@Test
public void TestEnrollUnenroll() throws SQLException{
	try {
		Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// throw new ServletException(e);
		}  
		EnrollmentDao edao = new EnrollmentDaoImpl();
		User user = new User(2,"Elnaz","Bayazian","ebayazian","student123","ebayasian@calstatela.edu",123456789);
		Assert.assertNull(edao.getEnrollment(user, "summer 2014", 3));
		edao.enroll(user, "summer 2014" ,3);
	  	Assert.assertNotNull(edao.getEnrollment(user, "summer 2014", 3));
	    Assert.assertEquals(edao.getEnrollment(user, "summer 2014", 3).getSchedule().getId(), 3);
	    Assert.assertEquals(edao.getEnrollment(user, "summer 2014", 3).getUser().getUsername(), "ebayazian");
	    edao.unEnroll(user,"summer 2014" ,3);
	    Assert.assertNull(edao.getEnrollment(user, "summer 2014", 3));
		}
@Test
public void TestUserSchedule()throws SQLException{
	try {
		Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// throw new ServletException(e);
		}  
	    EnrollmentDao edao = new EnrollmentDaoImpl();
	    User user = new User(2,"Elnaz","Bayazian","ebayazian","student123","ebayasian@calstatela.edu",123456789);
	    Assert.assertEquals(edao.getUserSchedule(user,"summer 2014").size(), 0);
	    edao.enroll(user, "summer 2014" ,3);
	    Assert.assertEquals(edao.getUserSchedule(user,"summer 2014").size(), 1);
	    edao.unEnroll(user,"summer 2014" ,3);
	    Assert.assertEquals(edao.getUserSchedule(user,"summer 2014").size(), 0);
	    
}
}
