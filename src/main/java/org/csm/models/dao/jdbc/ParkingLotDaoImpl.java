package org.csm.models.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.csm.models.ParkingLot;
import org.csm.models.dao.ParkingLotDao;

public class ParkingLotDaoImpl implements ParkingLotDao {
	@Override
	public List<ParkingLot> getParkingLots() throws SQLException {
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		Connection c = DriverManager.getConnection(url, myUsername, myPassword);
		PreparedStatement st = c.prepareStatement("select * from parkinglot");
		ResultSet rs = st.executeQuery();
		List<ParkingLot> parkinglots = new ArrayList<ParkingLot>();
		while (rs.next()) {
			parkinglots.add(new ParkingLot(rs.getInt("id"), rs
					.getDouble("latitude"), rs.getDouble("longitude"), rs
					.getInt("availablespots")));
		}	
		return parkinglots;
	}

	@Override
	public void incrementAvailableSpots(int id, int increment) throws SQLException {
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";		
		Connection c = DriverManager.getConnection(url, myUsername, myPassword);
		PreparedStatement st = c.prepareStatement("update parkinglots set availablespots = availablespots + ? where id=?");
		st.setInt(1, increment);
		st.setInt(2, id);
		st.executeUpdate();
	}

	@Override
	public void decrementAvailableSpots(int id, int decrement) throws SQLException {
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";		
		Connection c = DriverManager.getConnection(url, myUsername, myPassword);
		PreparedStatement st = c.prepareStatement("update parkinglots set availablespots = availablespots - ? where id=?");
		st.setInt(1, decrement);
		st.setInt(2, id);
		st.executeUpdate();
	}
}
