package org.csm.models;

import java.util.Date;



import com.google.gson.annotations.Expose;

public class Schedule {

	public Schedule(){
		
	}
	public Schedule(int id, int classNumber, int section, String component,
			String room, Date startDate, Date endDate, boolean status,
			Course course, int capacity) {
		super();
		this.id = id;
		this.classNumber = classNumber;
		this.section = section;
		this.component = component;
		this.room = room;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.course = course;
		this.capacity = capacity;
	}
	
	@Expose
	private int id;
	@Expose
	private int classNumber;
	@Expose
	private int section;
	@Expose
	private String component;
	@Expose
	private String room;
	@Expose
	private Date startDate;
	@Expose
	private Date endDate;
	@Expose
	private boolean status;
	@Expose
	private Course course;
	@Expose
	private int capacity;
	@Expose
	private Department department;
	@Expose
	private Term term;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}

	
}
