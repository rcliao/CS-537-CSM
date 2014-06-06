package org.csm.models.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.csm.models.Announcement;
import org.csm.models.dao.AnnouncementDao;

public class AnnouncementDaoImpl implements AnnouncementDao{

	@Override
	public void addAnnouncement(Announcement a) throws SQLException {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		Connection c = DriverManager.getConnection(url, myUsername, myPassword);
		PreparedStatement st = c.prepareStatement("insert into announcements values (null, ?, ?)");
		st.setTimestamp(1, new Timestamp(a.getTimestamp().getTime()));
		st.setString(2, a.getText());
		st.executeUpdate();
	}

	@Override
	public List<Announcement> getAnnouncements() throws SQLException {
		// TODO Auto-generated method stub
		List<Announcement> as = new ArrayList<Announcement>();
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		Connection c = DriverManager.getConnection(url, myUsername, myPassword);
		PreparedStatement st = c.prepareStatement("select * from announcements");
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			as.add(new Announcement(rs.getInt("id"), rs.getTimestamp("timestamp"), rs.getString("text")));
		}	
		return as;
	}

	@Override
	public void deleteAnnouncement(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost/csm";
		String myUsername = "csm_admin";
		String myPassword = "csm_admin";
		Connection c = DriverManager.getConnection(url, myUsername, myPassword);
		PreparedStatement st = c.prepareStatement("delete from announcements where id=?");
		st.setInt(1, id);
		st.executeUpdate();
	}

		
}
