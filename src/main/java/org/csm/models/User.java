package org.csm.models;

import com.google.gson.annotations.Expose;

public class User {
	@Expose
	private String username;

	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}