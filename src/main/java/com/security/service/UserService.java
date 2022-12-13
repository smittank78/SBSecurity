package com.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import com.security.dto.UserDto;

@Service
public class UserService 
{
	@Autowired
	private JdbcUserDetailsManager manager;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private UserDetails user;
	
	
	public void createUser(UserDto dto)
	{
		System.out.println("user :  " + dto.getUser() + " creating");	
		user  = User
		          	.withUsername(dto.getUser())
		            .password(dto.getPass())
		            .roles(dto.getRole())
		            .passwordEncoder((pass)-> encoder.encode(pass) )
		            .build();
		
		System.out.println("user :  " + user.getUsername() + " created");			
		manager.createUser(user);	
	}
	public List<UserDto> selectUser(String user)
	{
		List<UserDto> userList = new ArrayList<>();
		String existQuery = "select count(USERNAME) as count from users where USERNAME = ?;";
		userList= jdbc.query(existQuery,
				(rs, rowNum) -> new UserDto(rs.getString("USERNAME")	
							,rs.getString("PASSWORD")
							,(rs.getString("ENABLED").equals("1") ) ? true : false
							,null)
				, user);
				
		return userList;
	}
	public void deleteUser(UserDto dto)
	{
		manager.deleteUser(dto.getUser());	
	}
	public void resetPassward(UserDto dto)
	{
		String resetPasswardQuery = "update users set PASSWORD = ? where USERNAME = ?;";
		jdbc.update(resetPasswardQuery,encoder.encode(dto.getPass()),dto.getUser());
		System.out.println("user passward reset done for : " + dto.getUser() );
	}	
}