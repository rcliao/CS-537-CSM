package org.csm.controllers;

import java.util.Date;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class EmailControllerTest extends JerseyTest {

	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}

	@Test
	public void sendEmail(){
		WebResource webResource = client().resource(
				"http://localhost:8080/csm/rest/Email/");
		String responseMsg = null;
		String to = "rcliao01@gmail.com";
		String text = "This is a jersey test for project csm\nDate: " + new Date().toString();
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("to", to);
		params.add("text", text);
		try {
			responseMsg = webResource.path("sendEmail")
					.queryParams(params)
					.header("Authorization", "Basic c3NhZWVkaTpzdHVkZW50MTIz")
					.post(String.class);
		} catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
