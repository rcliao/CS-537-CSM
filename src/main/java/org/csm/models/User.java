package org.csm.models;

import com.google.gson.annotations.Expose;

public class User {
	@Expose
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private int cin;
	
	public User(int id, String firstName, String lastName, String username,
			String password,String email, int cin) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.cin = cin;
	}
	
	public User(String username){
		super();
		this.username=username;
	}
	
	public int getId() {
		return id;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	
}