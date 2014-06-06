package org.csm.models;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class Announcement {
	
	
	public Announcement(int id, Date timestamp, String text) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.text = text;
	}
	
	public Announcement(Date timestamp, String text) {
		super();
		this.timestamp = timestamp;
		this.text = text;
	}

	@Expose
	private int id;
	@Expose
	private Date timestamp;
	@Expose
	private String text;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
