package org.csm.models.dao;

import java.sql.SQLException;
import java.util.List;

import org.csm.models.Announcement;

public interface AnnouncementDao {
	public void addAnnouncement(Announcement a) throws SQLException;
	public List<Announcement> getAnnouncements() throws SQLException;
	public void deleteAnnouncement(Integer id) throws SQLException;
}
