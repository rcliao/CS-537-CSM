package org.csm.models;

import com.google.gson.annotations.Expose;

public class Department {
	public Department(){
		
	}
	@Expose
	private int id;
	@Expose
	private String name;
	@Expose
	private String phone;
	@Expose
	private String abbreviation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	
}
