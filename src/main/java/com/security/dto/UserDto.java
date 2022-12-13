package com.security.dto;

public class UserDto 
{
	private String user;
	private String pass;
	private boolean enable;
	private String role;
	
		
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(String user, String pass, boolean enable, String role) {
		super();
		this.user = user;
		this.pass = pass;
		this.enable = enable;
		this.role = role;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserDto [user=" + user + ", pass=" + pass + ", enable=" + enable + ", role=" + role + "]";
	}

}