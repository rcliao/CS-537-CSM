package org.csm.models;

import com.google.gson.annotations.Expose;

public class Course {
	
	public Course(int id, String subject, int number, boolean credit, int units) {
		super();
		this.id = id;
		this.subject = subject;
		this.number = number;
		this.credit = credit;
		this.units = units;
	}
	public Course(){
		
	}
	@Expose
	private int id;
	@Expose
	private String subject;
	@Expose
	private int number;
	@Expose
	private String description;
	@Expose
	private boolean credit;
	@Expose
	private int units;
	@Expose
	private String type;
	@Expose
	private String career;
	@Expose
	private String status;
	@Expose
	private Department department;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isCredit() {
		return credit;
	}
	public void setCredit(boolean credit) {
		this.credit = credit;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
}
