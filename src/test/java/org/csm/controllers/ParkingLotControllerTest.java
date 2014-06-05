package org.csm.controllers;

import java.util.List;

import org.csm.models.ParkingLot;
import org.csm.models.Schedule;
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


public class ParkingLotControllerTest extends JerseyTest {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	
	@Test
	public void getAvailableSpots(){
		WebResource webResource = client().resource(
				"http://localhost:8080/csm/rest/ParkingLot/");
		String responseMsg = null;
		try {
			responseMsg = webResource.path("getAvailableSpots")
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
		java.lang.reflect.Type type = new TypeToken<List<ParkingLot>>(){}.getType();
		List<ParkingLot> pl = gson.fromJson(responseMsg, type);
		assert pl.size() == 5;
		assert pl.get(0).getLatitude() == 34.070175;
	}
}
