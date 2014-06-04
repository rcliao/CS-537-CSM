package org.csm.models.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.csm.models.Course;
import org.csm.models.Enrollment;
import org.csm.models.Schedule;
import org.csm.models.Term;
import org.csm.models.User;
import org.csm.models.dao.EnrollmentDao;

public class EnrollmentDaoImpl implements EnrollmentDao{

	@Override
	public void enroll(User u,String term, Integer scheduleId) throws SQLException {
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		Connection c = null;
		ResultSet result;
		Integer enrollment_id = null;
		try {
			c = DriverManager.getConnection(url, myUsername, myPassword);
			c.setAutoCommit(false);
		// Enrollment status: 1- 'Enrollment Authorized' 	--> ('EA')
		//                    2-'Enrollment Unauthorized'	--> ('EU')
		//                    3-'Course-Selected'			--> ('CS')
		//                    4-'Course-Added'				--> ('CA')
		//                    5-'Course-Dropped'			--> ('CD')
		//                    6='Course-Withdrawn			--> ('CW')
		//                    7-'Course-Passed				--> ('CP')
		//                    8-'Course-Failed				--> ('CF')

		// fetching the term to get id
			
		Enrollment enrollment = getEnrollment( u,  term, scheduleId);
		if (enrollment == null)
		{
		String sqlStatement = "select * from terms where description = ? ;";
		PreparedStatement stmt = c.prepareStatement(sqlStatement);
		stmt.setString(1, term);
		result = stmt.executeQuery();
		if( result.next())
		{
		Integer id = result.getInt("id");
	
		// preparing statement to insert the course into enrollment
		sqlStatement = "insert into enrollment (term,status,users_id,schedule_id)" +
								"values(?,?,?,?); ";
		stmt = c.prepareStatement(sqlStatement);
		stmt.setInt(1,id);
		stmt.setString(2, "CA");
		stmt.setInt(3,u.getId());
		stmt.setInt(4,scheduleId);
		Boolean rs = stmt.execute();
		// update schedule table if user enrolled in course
		sqlStatement = "update schedule set capacity=capacity-1 ," +
						"status=case when capacity<=1 then false else true end where id=?;" ;
		stmt=c.prepareStatement(sqlStatement);
		stmt.setInt(1, scheduleId);
		rs = stmt.execute();
		//sqlStatement="SELECT LAST_INSERT_ID();";
		//stmt=c.prepareStatement(sqlStatement);
		//result=stmt.executeQuery();
		//enrollment_id= result.getInt("last_insert_id()");
		c.commit();
		}
		}
		else
			System.err.println("You have already enrolled in this class!");
		} catch (SQLException e) {
			System.err.println("SQL Exception");
		    System.err.println(e.getMessage());
		    c.rollback();
		}
		finally{
			c.close();
		}
		

	}

	@Override
	public void unEnroll(User u,String term, Integer scheduleId) throws SQLException {
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		Connection c=null;
		try {
			c = DriverManager.getConnection(url, myUsername, myPassword);
			c.setAutoCommit(false);
		String sqlStatement = "delete from enrollment where users_id=? and schedule_id=? and " +
							"term=(select id from terms where description=?) ;";
		PreparedStatement stmt = c.prepareStatement(sqlStatement);
		stmt.setInt(1, u.getId());
		stmt.setInt(2,scheduleId);
		stmt.setString(3, term);
		/*
		 * The execute method returns a boolean to indicate the form of the first result.
		 *  You must call either the method getResultSet or getUpdateCount to retrieve the result; 
		 *  you must call getMoreResults to move to any subsequent result(s).

		Returns:
			true if the first result is a ResultSet object; false if the first result is an update count
			 or there is no result 

		 */
	    Boolean rs = stmt.execute();
	    if (stmt.getUpdateCount()!=0)
	    {
	    sqlStatement = "update schedule set capacity=capacity+1 ," +
				"status=case when capacity<=0 then false else true end where id=? ;" ;
	    stmt=c.prepareStatement(sqlStatement);
	    stmt.setInt(1, scheduleId);
	    rs = stmt.execute();
	    c.commit();
	    }
	    else
	    	c.rollback();
	  
		} catch (SQLException e) {
			System.err.println("SQL Exception");
		    System.err.println(e.getMessage());
		    c.rollback();
		}
		finally{
			  c.close();
		}
	}

	@Override
	public List<Schedule> getUserSchedule(User u, String term) throws SQLException {
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		Connection c;
		c = DriverManager.getConnection(url, myUsername, myPassword);
		String sqlStatement="select s.id as schedule_id,e.id as enrollment_id, e.status as enrollment_status," +
							" s.status as schedule_status , t.id as term_id,t.status as term_status," +
							"s.class_number, s.section, s.component, s.room, " +
							"s.start_date, s.end_date, s.status, c.id as course_id, c.subject, c.number, c.credit, c.units," +
							" s.capacity " +
							" from enrollment e inner join terms t on (e.term=t.id )" +
							" inner join schedule s on (e.schedule_id=s.id  ) " +
							"inner join courses c on s.courses_id=c.id " +
							" where t.description=? and  users_id=? ";
		PreparedStatement stmt = c.prepareStatement(sqlStatement);
		stmt.setString(1, term);
		stmt.setInt(2,u.getId());
		ResultSet rs = stmt.executeQuery();
		List<Schedule> schedules = new ArrayList<Schedule>();
		while (rs.next()) {
			schedules.add(new Schedule(rs.getInt("schedule_id"), rs
						.getInt("class_number"), rs.getInt("section"), rs
						.getString("component"), rs.getString("room"), rs
						.getDate("start_date"), rs.getDate("end_date"), rs
						.getBoolean("schedule_status"), new Course(rs.getInt("course_id"),
						rs.getString("subject"), rs.getInt("number"), rs
								.getBoolean("credit"), rs.getInt("units")), rs
						.getInt("capacity")));

			}
		return schedules;
	}

	@Override
	public Enrollment getEnrollment(User u, String term, Integer scheduleId) throws SQLException {
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		Connection c;
		c = DriverManager.getConnection(url, myUsername, myPassword);
		String sqlStatement="select s.id as schedule_id,e.id as enrollment_id, e.status as enrollment_status," +
				            " s.status as schedule_status , t.id as term_id,t.status as term_status, t.description," +
				            "s.class_number, s.section, s.component, s.room, " +
				            "s.start_date, s.end_date, s.status, c.id as course_id, c.subject, c.number, c.credit, c.units," +
				            " s.capacity " +
				            " from enrollment e inner join terms t on (e.term=t.id )" +
				            " inner join schedule s on (e.schedule_id=s.id  ) " +
				            "inner join courses c on s.courses_id=c.id " +
				            " where t.description=? and  users_id=? and e.schedule_id=?";
		
		PreparedStatement stmt = c.prepareStatement(sqlStatement);
		stmt.setString(1, term);
		stmt.setInt(2,u.getId());
		stmt.setInt(3, scheduleId);
		ResultSet rs = stmt.executeQuery();
		Enrollment enrollment = null;
		while (rs.next()) {
			 enrollment=new Enrollment(rs.getInt("enrollment_id"),
					                             new Term(rs.getInt("term_id"),rs.getString("description"),rs.getBoolean("term_status")),
					                             rs.getString("enrollment_status"),
					                             new Schedule(rs.getInt("schedule_id"), rs
						                                      .getInt("class_number"), rs.getInt("section"), rs
					                                          .getString("component"), rs.getString("room"), rs
						                                      .getDate("start_date"), rs.getDate("end_date"), rs
						                                      .getBoolean("schedule_status"), new Course(rs.getInt("course_id"),
						                                      rs.getString("subject"), rs.getInt("number"), rs
								                              .getBoolean("credit"), rs.getInt("units")), rs
						                                      .getInt("capacity")),u);

			}
	  return enrollment;
	} 

}
