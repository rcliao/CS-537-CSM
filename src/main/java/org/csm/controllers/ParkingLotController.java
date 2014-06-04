package org.csm.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.csm.models.ParkingLot;
import org.csm.models.dao.ParkingLotDao;
import org.csm.models.dao.jdbc.ParkingLotDaoImpl;

@Path("/ParkingLot")
public class ParkingLotController {
	private ParkingLotDao parkingLotDao = new ParkingLotDaoImpl();

	@GET
	@Path("/getAvailableSpots")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ParkingLot> getAvailableSpots() throws SQLException {
		return parkingLotDao.getParkingLots();
	}

	@POST
	@Path("/incrementAvailableSpots/{id}")
	public void incrementAvailableSpots(@PathParam("id") int id,
			@QueryParam("increment") int increment) throws SQLException {
		parkingLotDao.incrementAvailableSpots(id, increment);
	}
	
	@POST
	@Path("/decrementAvailableSpots/{id}")
	public void decrementAvailableSpots(@PathParam("id") int id,
			@QueryParam("increment") int decrement) throws SQLException {
		parkingLotDao.decrementAvailableSpots(id, decrement);
	}
}
