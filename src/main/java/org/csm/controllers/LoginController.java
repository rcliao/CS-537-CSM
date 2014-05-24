package org.csm.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.csm.models.User;
import org.csm.models.dao.UserDao;
import org.csm.models.dao.jdbc.UserDaoImpl;
import org.csm.models.JsonObjects.LoginSuccess;

import com.google.gson.annotations.Expose;

@Path("/login")
public class LoginController {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginSuccess login(User user, @Context HttpServletRequest request){
		User u = null;
		String message = "";
		UserDao userdao = new UserDaoImpl();
		try{
			u = userdao.getUser(user.getUsername(), user.getPassword());
			
		}
		catch(SQLException e){	
			message = e.getMessage();
		}
		if(u != null){
			HttpSession session = request.getSession(true);
			session.setAttribute("login-name", u.getUsername());
			return new LoginSuccess(true);
		}
		return new LoginSuccess(false, message);
	}
}
