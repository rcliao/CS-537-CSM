package org.csm.controllers;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.csm.models.Schedule;
import org.csm.models.User;
import org.csm.models.dao.EnrollmentDao;
import org.csm.models.dao.ScheduleDao;
import org.csm.models.dao.UserDao;
import org.csm.models.dao.jdbc.EnrollmentDaoImpl;
import org.csm.models.dao.jdbc.ScheduleDaoImpl;
import org.csm.models.dao.jdbc.UserDaoImpl;
import org.csm.util.BasicAuth;

@Path("/GET")
public class GETController {
	private UserDao userDao = new UserDaoImpl();
	private ScheduleDao scheduleDao = new ScheduleDaoImpl();
	private EnrollmentDao enrollmentDao = new EnrollmentDaoImpl();

	@GET
	@Path("/schedules/{courseName}/{term}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Schedule> getSchedules(
			@PathParam("courseName") String courseName,
			@PathParam("term") String term,
			@QueryParam("available") Boolean available) {
		if (term == null || term.isEmpty()) {
			Calendar c = Calendar.getInstance();
			int month = c.get(Calendar.MONTH);
			if (month < 3)
				term = "winter ";
			else if (month < 6)
				term = "spring ";
			else if (month < 9)
				term = "summer ";
			else
				term = "fall ";
			term += c.get(Calendar.YEAR);
		}
		try {
			if (available != null)
				return scheduleDao.getSchedules(courseName, available, term);
			else
				return scheduleDao.getSchedules(courseName, term);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}

	@POST
	@Path("/enroll/{scheduleId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String enroll(@PathParam("scheduleId") Integer scheduleId,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws SQLException {
		User u = userDao.getUser(BasicAuth.decode(request.getHeader("Authorization"))[0]);
		String term = "";
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH)+3;
		if (month < 3)
			term = "winter ";
		else if (month < 6)
			term = "spring ";
		else if (month < 9)
			term = "summer ";
		else
			term = "fall ";
		term += c.get(Calendar.YEAR);
		System.out.println(scheduleId);
		enrollmentDao.enroll(u, term, scheduleId);
		response.setStatus(201);
		return null;
	}

	@GET
	@Path("/userSchedules")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Schedule> getUserSchedules(@Context HttpServletRequest request) throws SQLException{
		User u = userDao.getUser(BasicAuth.decode(request.getHeader("Authorization"))[0]);
		String term = "";
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH)+3;
		if (month < 3)
			term = "winter ";
		else if (month < 6)
			term = "spring ";
		else if (month < 9)
			term = "summer ";
		else
			term = "fall ";
		term += c.get(Calendar.YEAR);
		return enrollmentDao.getUserSchedule(u.getUsername(),term);
	}


	@DELETE
	@Path("/enroll/{scheduleId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String UnEnroll(@PathParam("scheduleId") Integer scheduleId,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws SQLException {
		User u = userDao.getUser(BasicAuth.decode(request.getHeader("Authorization"))[0]);
		String term = "";
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH)+3;
		if (month < 3)
			term = "winter ";
		else if (month < 6)
			term = "spring ";
		else if (month < 9)
			term = "summer ";
		else
			term = "fall ";
		term += c.get(Calendar.YEAR);
		enrollmentDao.unEnroll(u, term, scheduleId);
		response.setStatus(201);
		return null;
	}

}
