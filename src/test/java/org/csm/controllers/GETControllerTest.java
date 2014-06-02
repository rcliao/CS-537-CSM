package org.csm.controllers;

import static org.junit.Assert.*;

import java.net.URISyntaxException;

import org.junit.Test;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class GETControllerTest extends JerseyTest {

	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}

	@Test
	public void schudule() throws URISyntaxException {
		WebResource webResource = client().resource("http://localhost:8080/csm/rest/GET/");		
		String responseMsg = null;
		try {
			webResource.header("Authorization", "Basic c3NhZWVkaTpzdHVkZW50MTIz");
			responseMsg = webResource.path(
					"schedules/cs537/summer%202014").get(String.class);
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
		System.out.println(responseMsg);
	}
}
