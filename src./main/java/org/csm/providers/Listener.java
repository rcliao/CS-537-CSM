package org.csm.providers;

import javax.ws.rs.ext.Provider;

import com.sun.jersey.api.model.AbstractResourceModelContext;
import com.sun.jersey.api.model.AbstractResourceModelListener;

// This should load only once on application startup
@Provider
public class Listener implements AbstractResourceModelListener {
    @Override
    public void onLoaded(AbstractResourceModelContext modelContext) {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// throw new ServletException(e);
		}        
    }
}