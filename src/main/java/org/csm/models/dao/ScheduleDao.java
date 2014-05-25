package org.csm.models.dao;

import java.sql.SQLException;
import java.util.List;

import org.csm.models.Schedule;
import org.csm.models.User;

public interface ScheduleDao {
	public List<Schedule> getSchedules(String courseName, boolean available, String term) throws SQLException;
	//public List<Schedule> getSchedules(String courseName) throws SQLException;
	public List<Schedule> getSchedules(String courseName, String term) throws SQLException;
	public List<Schedule> getSchedulesDistinct(String courseName, boolean available, String term) throws SQLException;
	//public List<Schedule> getSchedules(String courseName) throws SQLException;
	public List<Schedule> getSchedulesDistinct(String courseName, String term) throws SQLException;
	//enroll User u in the schedule 
	public void enroll(User u, Integer scheduleId);
	//UnEnroll User u in the schedule
	public void unEnroll(User u, Integer scheduleId);
}
