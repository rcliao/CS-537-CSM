package org.csm.models.dao.jdbc;

import org.csm.models.User;
import org.csm.models.dao.UserDao;

public class UserDaoImpl implements UserDao {
	@Override
	public User getUser(String username){
		// This is where sql scripts would go
		return new User(username);
	}
}
