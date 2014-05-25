package org.csm.models;

import java.sql.SQLException;

import junit.framework.Assert;

import org.csm.models.dao.ScheduleDao;
import org.csm.models.dao.jdbc.ScheduleDaoImpl;
import org.junit.Test;

public class ScheduleTest {
	@Test
	public void TestGetSchedules_1() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// throw new ServletException(e);
		}  
		ScheduleDao sdao = new ScheduleDaoImpl();
		Assert.assertEquals(sdao.getSchedules("cs537", true, "summer 2014").size(), 1);
		/*assert sdao.getSchedules("cs537", true, "summer 2014").get(0).getRoom().equals("E&T A-315");
		assert sdao.getSchedules("cs537", "summer 2014").size() == 1;
		assert sdao.getSchedules("cs537", "summer 2014").get(0).getRoom().equals("E&T A-315");
		assert sdao.getSchedules("cs537", false, "summer 2014").size() == 0;*/
	}
	
	@Test
	public void TestGetSchedules_2() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// throw new ServletException(e);
		}  
		ScheduleDao sdao = new ScheduleDaoImpl();
	//	Assert.assertEquals(sdao.getSchedules("cs537", true, "summer 2014").size(), 1);
		assert sdao.getSchedules("cs537", true, "summer 2014").get(0).getRoom().equals("E&T A-315");
	//	assert sdao.getSchedules("cs537", "summer 2014").size() == 1;
	//	assert sdao.getSchedules("cs537", "summer 2014").get(0).getRoom().equals("E&T A-315");
	//	assert sdao.getSchedules("cs537", false, "summer 2014").size() == 0;
	}
	
	@Test
	public void TestGetSchedules_3() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// throw new ServletException(e);
		}  
		ScheduleDao sdao = new ScheduleDaoImpl();
	//	Assert.assertEquals(sdao.getSchedules("cs537", true, "summer 2014").size(), 1);
		//assert sdao.getSchedules("cs537", true, "summer 2014").get(0).getRoom().equals("E&T A-315");
		assert sdao.getSchedules("cs537", "summer 2014").size() == 1;
	//	assert sdao.getSchedules("cs537", "summer 2014").get(0).getRoom().equals("E&T A-315");
	//	assert sdao.getSchedules("cs537", false, "summer 2014").size() == 0;
	}

	@Test
	public void TestGetSchedules_4() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// throw new ServletException(e);
		}  
		ScheduleDao sdao = new ScheduleDaoImpl();
	/*	Assert.assertEquals(sdao.getSchedules("cs537", true, "summer 2014").size(), 1);
		assert sdao.getSchedules("cs537", true, "summer 2014").get(0).getRoom().equals("E&T A-315");
		assert sdao.getSchedules("cs537", "summer 2014").size() == 1;*/
		assert sdao.getSchedules("cs537", "summer 2014").get(0).getRoom().equals("E&T A-315");
	//	assert sdao.getSchedules("cs537", false, "summer 2014").size() == 0;
	}
	@Test
	public void TestGetSchedules_5() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// throw new ServletException(e);
		}  
		ScheduleDao sdao = new ScheduleDaoImpl();
	/*	Assert.assertEquals(sdao.getSchedules("cs537", true, "summer 2014").size(), 1);
		assert sdao.getSchedules("cs537", true, "summer 2014").get(0).getRoom().equals("E&T A-315");
		assert sdao.getSchedules("cs537", "summer 2014").size() == 1;
		assert sdao.getSchedules("cs537", "summer 2014").get(0).getRoom().equals("E&T A-315");*/
		assert sdao.getSchedules("cs537", false, "summer 2014").size() == 0;
	}
}
