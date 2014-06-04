package org.csm.controllers;

import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.csm.models.Schedule;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
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
		WebResource webResource = client().resource(
				"http://localhost:8080/csm/rest/GET/");
		String responseMsg = null;
		try {
			responseMsg = webResource.path("schedules/cs537/summer%202014")
					.header("Authorization", "Basic c3NhZWVkaTpzdHVkZW50MTIz")
					.get(String.class);
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
		// System.out.println(responseMsg);		
		Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
		java.lang.reflect.Type type = new TypeToken<List<Schedule>>(){}.getType();
		List<Schedule> sc = gson.fromJson(responseMsg, type);
		assert sc.size() == 1;
		assert sc.get(0).getId() == 2;
				
	}
}
