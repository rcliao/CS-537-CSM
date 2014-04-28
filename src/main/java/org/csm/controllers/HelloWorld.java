package org.csm.controllers;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Plain old Java Object it does not extend as class or implements 
// an interface
import org.csm.models.User;
import org.csm.models.dao.*;

//Sets the path to base URL + /hello
@Path("/hello")
public class HelloWorld {
	

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHelloUser(@PathParam("username") String username,String password) {
		UserDao userDao = null;
		try {
			// password is hard-coded for test purposes
			return  "Hello " + userDao.getUser(username,"student123" ).getUsername();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return " Hello guest";
	}

} 