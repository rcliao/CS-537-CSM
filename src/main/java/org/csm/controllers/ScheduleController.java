package org.csm.controllers;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.csm.models.Schedule;
import org.csm.models.dao.ScheduleDao;
import org.csm.models.dao.jdbc.ScheduleDaoImpl;

@Path("/schedules")
public class ScheduleController {
	
	@POST
	@Path("/{courseName}/{term}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Schedule> getSchedules(@PathParam("courseName") String courseName, 
			@PathParam("term") String term, @Context HttpServletRequest request, 
			@Context HttpServletResponse response){
		ScheduleDao schedule = new ScheduleDaoImpl();
		HttpSession session = request.getSession(true);
		if(term == null || term.isEmpty())
		{
			Calendar c = Calendar.getInstance();
			int month = c.get(Calendar.MONTH);
			if(month < 4)
				term = "winter ";
			else if(month < 7)
				term = "spring ";
			else if (month < 10)
				term = "summer ";
			else term = "fall ";
			term += c.get(Calendar.YEAR);
		}
		try {
			return schedule.getSchedules(courseName, term);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@POST
	@Path("/{courseName}/{term}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Schedule> getSchedules(@PathParam("courseName") String courseName, 
			@PathParam("term") String term, @QueryParam("available") boolean available, @Context HttpServletRequest request, 
			@Context HttpServletResponse response){
		ScheduleDao schedule = new ScheduleDaoImpl();
		if(term == null || term.isEmpty())
		{
			Calendar c = Calendar.getInstance();
			int month = c.get(Calendar.MONTH);
			if(month < 4)
				term = "winter";
			else if(month < 7)
				term = "spring";
			else if (month < 10)
				term = "summer";
			else term = "fall";
			term += c.get(Calendar.YEAR);
		}
		try {
			return schedule.getSchedules(courseName, available, term);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
