package org.csm.models;

import java.util.List;

import com.google.gson.annotations.Expose;

public class Enrollment {

	@Expose
	private int id;
	@Expose
	private Term term;
	@Expose
	private String status;
	@Expose
	private Schedule schedule;
	private User user;
	
	public Enrollment()
	{
	}
	
	public Enrollment(int id, Term term, String status, Schedule schedule,
			User user) {
		super();
		this.id = id;
		this.term = term;
		this.status = status;
		this.schedule = schedule;
		this.user = user;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
