package org.csm.models.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.csm.models.User;
import org.csm.models.dao.UserDao;

public class UserDaoImpl implements UserDao {
	@Override

	// login validation
	public User getUser(String username, String password) throws SQLException{
			String url = "jdbc:mysql://localhost/csm";
			String myUsername = "csm_admin";
			String myPassword = "csm_admin";
			String sqlStatement;
			Connection c = DriverManager.getConnection(url, myUsername, myPassword);
			sqlStatement="select * from users where username=? and password=? ;";
			PreparedStatement stmt= c.prepareStatement(sqlStatement);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs= stmt.executeQuery();
			rs.next();
			User user=null;
			if (rs.getRow() != 0)
			{
				 user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
						         rs.getString("username"),  rs.getString("password"),rs.getString("email"),
						         rs.getInt("cin")) ;
			
			}
			c.close();
			return user;
	
		
		

	}

}
