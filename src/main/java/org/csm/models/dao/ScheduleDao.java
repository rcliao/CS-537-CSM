package org.csm.models.dao;

import java.sql.SQLException;
import java.util.List;

import org.csm.models.Schedule;

public interface ScheduleDao {
	public List<Schedule> getSchedules(String courseName, boolean available,String term) throws SQLException;
	public List<Schedule> getSchedules(String courseName,String term) throws SQLException;
	
}
