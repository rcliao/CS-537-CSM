package org.csm.models.dao;

import java.sql.SQLException;
import java.util.List;

import org.csm.models.Schedule;
import org.csm.models.Term;
import org.csm.models.User;

public interface ScheduleDao {
	
	public List<Schedule> getSchedules(String courseName, boolean available, String term) throws SQLException;
<<<<<<< HEAD
=======
	//public List<Schedule> getSchedules(String courseName) throws SQLException;
	public List<Schedule> getSchedules(String courseName, String term) throws SQLException;
	public List<Schedule> getSchedulesDistinct(String courseName, boolean available, String term) throws SQLException;
	//public List<Schedule> getSchedules(String courseName) throws SQLException;
	public List<Schedule> getSchedulesDistinct(String courseName, String term) throws SQLException;
	//enroll User u in the schedule 
	void enroll(User u, String term, Integer scheduleId);
	//UnEnroll User u in the schedule
	void unEnroll(User u, String term, Integer scheduleId);
	public List<Schedule> getUserSchedules(String username) throws SQLException;
>>>>>>> 594630f68a5fc7cade88d02a36d73de4e6d72371

	public List<Schedule> getSchedules(String courseName, String term) throws SQLException;
		
	// add course to watchlist
	//public List<Schedule> watchCourse(String courseName,String term,User u);
	
	// remove course from watchlist
	//public List<Schedule> unwatchCourse(String CourseName,String term, User u);
	
	
	
			
}
