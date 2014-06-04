package org.csm.models;

import com.google.gson.annotations.Expose;

public class ParkingLot {
	public ParkingLot(){
		
	}
	public ParkingLot(int id, double latitude, double longitude,
			int availableSpots) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.availableSpots = availableSpots;
	}
	@Expose
	private int id;
	@Expose
	private double latitude;
	@Expose
	private double longitude;
	@Expose
	private int availableSpots;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getAvailableSpots() {
		return availableSpots;
	}
	public void setAvailableSpots(int availableSpots) {
		this.availableSpots = availableSpots;
	}
	
	
}
