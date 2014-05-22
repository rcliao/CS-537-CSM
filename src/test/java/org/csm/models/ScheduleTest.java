package org.csm.models;

import java.sql.SQLException;

import org.csm.models.dao.ScheduleDao;
import org.csm.models.dao.jdbc.ScheduleDaoImpl;
import org.junit.Test;

public class ScheduleTest {
	
	public void TestGetSchedules() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// throw new ServletException(e);
		}  
		ScheduleDao sdao = new ScheduleDaoImpl();
		assert sdao.getSchedules("CS537", true, "summer 2014").size() == 1;
		assert sdao.getSchedules("CS537", true, "summer 2014").get(0).getRoom().equals("E&T A-315");
		assert sdao.getSchedules("CS537", "summer 2014").size() == 1;
		assert sdao.getSchedules("CS537", "summer 2014").get(0).getRoom().equals("E&T A-315");
		assert sdao.getSchedules("CS537", false, "summer 2014").size() == 0;
	}
}
