package org.csm.models.dao;

import java.sql.SQLException;
import java.util.List;

import org.csm.models.Schedule;
import org.csm.models.Term;
import org.csm.models.User;

public interface ScheduleDao {
	
	public List<Schedule> getSchedules(String courseName, boolean available, String term) throws SQLException;

	public List<Schedule> getSchedules(String courseName, String term) throws SQLException;
		
	// add course to watchlist
	//public List<Schedule> watchCourse(String courseName,String term,User u);
	
	// remove course from watchlist
	//public List<Schedule> unwatchCourse(String CourseName,String term, User u);
	
	
	
			
}
