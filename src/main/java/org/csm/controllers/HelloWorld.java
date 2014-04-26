package org.csm.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Plain old Java Object it does not extend as class or implements 
// an interface

import org.csm.models.User;

//Sets the path to base URL + /hello
@Path("/hello")
public class HelloWorld {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

	@Path("/user")
	@Produces("application/json")
	public User getHelloUser() {
		return new User("Hello User");
	}

} 