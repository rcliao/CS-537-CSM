package org.csm.models.dao;


import java.sql.SQLException;

import org.csm.models.User;

public interface UserDao {

public User getUser(String username, String password) throws SQLException;
public User getUser(String username) throws SQLException;

}
