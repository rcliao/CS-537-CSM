package org.csm.models.JSONObjects;

import com.google.gson.annotations.Expose;

public class LoginSuccess {
	public LoginSuccess(boolean s){
		success = s;
	}
	public LoginSuccess(boolean s, String m){
		success = s;
		message = m;
	}
	@Expose
	public boolean success;
	@Expose
	public String message;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean getSuccess(){
		return success;
	}
	public void setSuccess(boolean s){
		success = s;
	}
}
