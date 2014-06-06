package org.csm.controllers;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.csm.models.Announcement;
import org.csm.models.dao.AnnouncementDao;
import org.csm.models.dao.jdbc.AnnouncementDaoImpl;

@Path("/Announcement")
public class AnnouncementController {
	private AnnouncementDao adao = new AnnouncementDaoImpl();

	@POST
	@Path("/new")
	public void newAnnouncement(@QueryParam("timestamp") long timestamp,
			@QueryParam("text") String text) throws SQLException {
		Announcement a = new Announcement(new Date(timestamp), text);
		adao.addAnnouncement(a);
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> Announcements() throws SQLException {
		return adao.getAnnouncements();
	}

	@DELETE
	@Path("/delete/{id}")
	public void deleteAnnouncement(@PathParam("id") Integer id)
			throws SQLException {
		adao.deleteAnnouncement(id);
	}
}
