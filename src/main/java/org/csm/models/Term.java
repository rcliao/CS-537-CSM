package org.csm.models;

import com.google.gson.annotations.Expose;

public class Term {
	public Term(){
		
	}
	public Term(int id, String description, boolean status)
	{
		this.id = id;
		this.description = description;
		this.status = status;
	}
	@Expose
	private int id;
	@Expose
	private String description;
	@Expose
	private boolean status;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
