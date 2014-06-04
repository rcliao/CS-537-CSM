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
import org.csm.models.User;
import org.csm.models.dao.ScheduleDao;


public class ScheduleDaoImpl implements ScheduleDao {

	@Override
	public List<Schedule> getSchedules(String courseName, boolean available,
			String term) throws SQLException {
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		Connection c = DriverManager.getConnection(url, myUsername, myPassword);
		String cprefix = courseName.substring(0, 2);
		String cnumber = courseName.substring(2);
		String sqlStatement = "select s.id as schedule_id, s.class_number, s.section, s.component, s.room, " +
				"s.start_date, s.end_date, s.status, c.id as course_id, c.subject, c.number, c.credit, c.units," +
				" s.capacity "
				+ "from schedule s inner join courses c on s.courses_id=c.id join terms t on t.id = s.term " +
				"where s.status=? and c.subject=? and c.number=? and t.description=? ";
		PreparedStatement stmt = c.prepareStatement(sqlStatement);
		stmt.setBoolean(1, available);
		stmt.setString(2, cprefix.toLowerCase());
		stmt.setInt(3, Integer.parseInt(cnumber));
		stmt.setString(4, term.toLowerCase());
		ResultSet rs = stmt.executeQuery();
		List<Schedule> schedules = new ArrayList<Schedule>();
		while (rs.next()) {
			schedules.add(new Schedule(rs.getInt("schedule_id"), rs
					.getInt("class_number"), rs.getInt("section"), rs
					.getString("component"), rs.getString("room"), rs
					.getDate("start_date"), rs.getDate("end_date"), rs
					.getBoolean("status"), new Course(rs.getInt("course_id"),
					rs.getString("subject"), rs.getInt("number"), rs
							.getBoolean("credit"), rs.getInt("units")), rs
					.getInt("capacity")));

		}
		return schedules;
	}

	@Override
	public List<Schedule> getSchedules(String courseName, String term)
			throws SQLException {
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		Connection c = DriverManager.getConnection(url, myUsername, myPassword);
		String cprefix = courseName.substring(0, 2);
		String cnumber = courseName.substring(2);
		String sqlStatement = "select s.id as schedule_id, s.class_number, s.section, s.component, s.room," +
				" s.start_date, s.end_date, s.status, c.id as course_id, c.subject, c.number, c.credit, c.units," +
				" s.capacity "
				+ "from schedule s inner join courses c on s.courses_id=c.id join terms t on " +
				"t.id = s.term where c.subject=? and c.number=? and t.description=? ";
		PreparedStatement stmt = c.prepareStatement(sqlStatement);
		stmt.setString(1, cprefix.toLowerCase());
		stmt.setInt(2, Integer.parseInt(cnumber));
		stmt.setString(3, term.toLowerCase());
		ResultSet rs = stmt.executeQuery();
		List<Schedule> schedules = new ArrayList<Schedule>();
		while (rs.next()) {
			schedules.add(new Schedule(rs.getInt("schedule_id"), rs
					.getInt("class_number"), rs.getInt("section"), rs
					.getString("component"), rs.getString("room"), rs
					.getDate("start_date"), rs.getDate("end_date"), rs
					.getBoolean("status"), new Course(rs.getInt("course_id"),
					rs.getString("subject"), rs.getInt("number"), rs
							.getBoolean("credit"), rs.getInt("units")), rs
					.getInt("capacity")));

		}
		return schedules;
	}

	/*

	@Override
	public List<Schedule> watchCourse(String courseName, String term, User u) {
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		Connection c;
		c = DriverManager.getConnection(url, myUsername, myPassword);
		String sqlStatement =""
		return null;
	}

	@Override
	public List<Schedule> unwatchCourse(String CourseName, String term, User u) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	

	

	

}
