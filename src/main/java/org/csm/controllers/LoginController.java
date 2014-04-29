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

@Path("/login")
public class LoginController {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object login(User user, @Context HttpServletRequest request){
		User u = null;
		UserDao userdao = new UserDaoImpl();
		try{
			u = userdao.getUser(user.getUsername(), user.getPassword());
		}
		catch(SQLException e){		
		}
		if(u != null){
			HttpSession session = request.getSession(true);
			session.setAttribute("login-name", u.getUsername());
			return new Object(){
				public boolean Success = true;
			};
		}
		return new Object(){
			public boolean Success = false;
		};
	}
}
