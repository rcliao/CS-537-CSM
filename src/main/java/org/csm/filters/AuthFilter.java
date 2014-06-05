package org.csm.filters;

import java.sql.SQLException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.csm.models.User;
import org.csm.models.dao.UserDao;
import org.csm.models.dao.jdbc.UserDaoImpl;
import org.csm.util.BasicAuth;

import com.sun.jersey.core.header.InBoundHeaders;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class AuthFilter implements ContainerRequestFilter {
    /**
     * Apply the filter : check input request, validate or not with user auth
     * @param containerRequest The request from Tomcat server
     */

    @Override
    public ContainerRequest filter(ContainerRequest containerRequest) throws WebApplicationException {
        //GET, POST, PUT, DELETE, ...
        String method = containerRequest.getMethod();
        // myresource/get/56bCA for example
        String path = containerRequest.getPath(true);

        //We do allow wadl to be retrieved
        if(
            (method.equals("GET") &&
            (
                path.equals("application.wadl") ||
                path.equals("application.wadl/xsd0.xsd")
            )) ||
            path.contains("Account") // If user is trying to login
        ){
            return containerRequest;
        }

        //Get the authentication passed in HTTP headers parameters
        String auth = containerRequest.getHeaderValue("Authorization");

        //If the user does not have the right (does not provide any HTTP Basic Auth)
        if(auth == null){
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }

        //lap : loginAndPassword
        String[] lap = BasicAuth.decode(auth);

        //If login or password fail
        if(lap == null || lap.length != 2){
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }

        //DO YOUR DATABASE CHECK HERE (replace that line behind)...
        UserDao userDao = new UserDaoImpl();
        User authenticationResult = null;
		try {
			authenticationResult = userDao.getUser(lap[0], lap[1]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}

        //Our system refuse login and password
        if(authenticationResult == null){
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }

        //TODO : HERE YOU SHOULD ADD PARAMETER TO REQUEST, TO REMEMBER USER ON YOUR REST SERVICE...

        return containerRequest;
    }
}
