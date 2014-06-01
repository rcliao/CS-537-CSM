package org.csm.controllers;

import java.sql.SQLException;
import java.lang.Throwable;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.WebApplicationException;

import org.csm.models.User;
import org.csm.models.dao.UserDao;
import org.csm.models.dao.jdbc.UserDaoImpl;
import org.csm.models.JSONObjects.LoginSuccess;

import com.google.gson.annotations.Expose;

@Path("/Account")
public class AccountController {

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginSuccess login(User user){
		User u = null;
		String message = "";
		boolean success = false;
		UserDao userdao = new UserDaoImpl();
		try{
			System.out.println("user password: " + user.getPassword());
			// get user from user dao
			u = userdao.getUser(user.getUsername());

			if(u == null){
				throw new WebApplicationException(
					new Throwable("User does not exist"),
					404
				);
			}
			else if(!u.getPassword().equals(user.getPassword())){
				throw new WebApplicationException(
					new Throwable("Username/password do not match"),
					401
				);
			}
			else{
				success = true;
				message = "Login Successes";
			}
		}
		catch(SQLException e){
			throw new WebApplicationException(
				new Throwable(e.getMessage()),
				503
			);
		}
		return new LoginSuccess(success, message);
	}
}
