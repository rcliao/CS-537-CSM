package org.csm.models.dao;
import java.sql.SQLException;
import java.util.List;

import org.csm.models.Schedule;
import org.csm.models.User;
import org.csm.models.Enrollment;

public interface EnrollmentDao {
	
	//enroll User u in the schedule 
		void enroll(User u, String term, Integer scheduleId) throws SQLException;
	//UnEnroll User u in the schedule
		void unEnroll(User u, String term, Integer scheduleId) throws SQLException;
	// get user schedule
	//	public  List<Schedule> getUserSchedule(User u, String term) throws SQLException;
   // get enrollment
		public Enrollment getEnrollment(User u,String term,Integer scheduleId) throws SQLException;
		List<Schedule> getUserSchedule(String username, String term)
				throws SQLException;
}
