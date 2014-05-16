package org.csm.models.dao.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.csm.models.Course;
import org.csm.models.Schedule;
import org.csm.models.dao.ScheduleDao;

public class ScheduleDaoImpl implements ScheduleDao {

	@Override
	public List<Schedule> getSchedules(String courseName, boolean available, String term)
			throws SQLException {
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		String sqlStatement;
		Connection c = DriverManager.getConnection(url, myUsername, myPassword);
		String cprefix= courseName.substring(0,1);
		String cnumber = String.valueOf( courseName.substring(2));
		sqlStatement= "select * from schedule  s inner join courses c on (s.courses_id=c.id)  " +
				      " where s.status=? and c.subject=? and c.number=? and s.term=? ";
		PreparedStatement stmt= c.prepareStatement(sqlStatement);
		stmt.setBoolean(1, available);
		stmt.setString(2, cprefix);
		stmt.setString(3, cnumber);
		stmt.setString(4, term);
		ResultSet rs= stmt.executeQuery();
		List<Schedule> schedules =  new ArrayList<Schedule>();
		while (rs.next()){
			schedules.add(new Schedule(rs.getInt("s.id"),rs.getInt("class_number"),rs.getInt("section"),
					          rs.getString("component"),rs.getString("room"),rs.getDate("start_date"),
					          rs.getDate("end_date"),rs.getBoolean("status"),
					          new Course(rs.getInt("c.id"),rs.getString("subject"),rs.getInt("number"),
					        		     rs.getBoolean("credit"),rs.getInt("units")),rs.getInt("capacity")));
					
		}
		return schedules;
	}


	@Override
	public List<Schedule> getSchedules(String courseName, String term)
			throws SQLException {
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		String sqlStatement;
		Connection c = DriverManager.getConnection(url, myUsername, myPassword);
		String cprefix= courseName.substring(0,1);
		String cnumber = String.valueOf( courseName.substring(2));
		sqlStatement= "select * from schedule  s inner join courses c on (s.courses_id=c.id)  " +
				      " where  c.subject=? and c.number=? and s.term=? ";
		PreparedStatement stmt= c.prepareStatement(sqlStatement);
		stmt.setString(1, cprefix);
		stmt.setString(2, cnumber);
		stmt.setString(3, term);
		ResultSet rs= stmt.executeQuery();
		List<Schedule> schedules =  new ArrayList<Schedule>();
		while (rs.next()){
			schedules.add(new Schedule(rs.getInt("s.id"),rs.getInt("class_number"),rs.getInt("section"),
					          rs.getString("component"),rs.getString("room"),rs.getDate("start_date"),
					          rs.getDate("end_date"),rs.getBoolean("status"),
					          new Course(rs.getInt("c.id"),rs.getString("subject"),rs.getInt("number"),
					        		     rs.getBoolean("credit"),rs.getInt("units")),rs.getInt("capacity")));
					
		}
		return schedules;
	}

}
