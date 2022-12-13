package com.security.dto;

public class UserPwdResetDto 
{
	private String user;
	private String old_pass;
	private String new_pass;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getOld_pass() {
		return old_pass;
	}
	public void setOld_pass(String old_pass) {
		this.old_pass = old_pass;
	}
	public String getNew_pass() {
		return new_pass;
	}
	public void setNew_pass(String new_pass) {
		this.new_pass = new_pass;
	}	
	
}