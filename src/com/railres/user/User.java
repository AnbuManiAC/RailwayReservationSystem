package com.railres.user;

public class User {
	
	String username;
	String password;
	
	public User(String username, String password) {
		this.setUsername(username);;
		this.setPassword(password);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
