package org.csm.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.csm.models.JSONObjects.LoginSuccess;

import com.google.gson.annotations.Expose;

@Path("/Account")
public class AccountController {
	
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginSuccess login(User user, @Context HttpServletRequest request, @Context HttpServletResponse response){
		User u = null;
		String message = "";
		boolean success = false;
		UserDao userdao = new UserDaoImpl();
		try{
			u = userdao.getUser(user.getUsername());
			if(u == null){
				response.setStatus(404);
				message = "User does not exist";
			}
			else if(!u.getPassword().equals(user.getPassword())){
				response.setStatus(401);
				message = "Password is incorrect";
			}
			else{
				HttpSession session = request.getSession(true);
				session.setAttribute("loginUser", u);
				success = true;
			}
		}
		catch(SQLException e){	
			message = e.getMessage();
			response.setStatus(500);
		}		
		return new LoginSuccess(success, message);
	}
	@POST
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginSuccess logout(User user, @Context HttpServletRequest request, @Context HttpServletResponse response){
		HttpSession session = request.getSession();
		session.invalidate();
		return new LoginSuccess(true, "");
	}
}
