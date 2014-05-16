package org.csm.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
			@PathParam("term") String term){
		ScheduleDao schedule = new ScheduleDaoImpl();
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
			@PathParam("term") String term, @QueryParam("available") boolean available){
		ScheduleDao schedule = new ScheduleDaoImpl();
		try {
			return schedule.getSchedules(courseName, available, term);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
