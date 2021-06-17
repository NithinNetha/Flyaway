package com.flyaway.model;

public class Users {
	private String username;
	private String password;
	private String Guest;
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
	public String getGuest() {
		return Guest;
	}
	public void setGuest(String guest) {
		Guest = guest;
	}
	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + ", Guest=" + Guest + "]";
	}
	
}
