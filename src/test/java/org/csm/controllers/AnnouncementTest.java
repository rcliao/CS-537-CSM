package org.csm.controllers;

import java.util.List;

import org.csm.models.Announcement;
import org.csm.models.ParkingLot;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class AnnouncementTest extends JerseyTest{
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	
	@Test
	public void getAnnouncements(){
		WebResource webResource = client().resource(
				"http://localhost:8080/csm/rest/Announcement/");
		String responseMsg = null;
		try {
			responseMsg = webResource.path("/list")
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
		Gson gson = new GsonBuilder().create();
		java.lang.reflect.Type type = new TypeToken<List<Announcement>>(){}.getType();
		List<Announcement> pl = gson.fromJson(responseMsg, type);
		assert pl.get(0).getText().equals("some test announcement");
	}
	
}
