package org.csm.models.dao;

import java.sql.SQLException;
import java.util.List;

public interface Schedule {
	public List<Schedule> getSchedules(String courseName, boolean available) throws SQLException;
	public List<Schedule> getSchedules(String courseName) throws SQLException;
	
}
