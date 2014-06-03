package org.csm.models.dao;

import java.sql.SQLException;
import java.util.List;

import org.csm.models.ParkingLot;

public interface ParkingLotDao {
	public List<ParkingLot> getParkingLots() throws SQLException;
	public void incrementAvailableSpots(int id, int increment) throws SQLException;
	public void decrementAvailableSpots(int id, int decrement) throws SQLException;
}
