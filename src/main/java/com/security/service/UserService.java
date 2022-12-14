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
	private JdbcUserDetailsManager jdbcManager;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private UserDetails user;
	
	/*
	 * create UserDetails Object
	 * using jdbc manager store user into db
	 */
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
		jdbcManager.createUser(user);	
		System.out.println("user :  " + user.getUsername() + " created in db");	
	}
	
	public List<UserDto> selectUser(String user)
	{
		List<UserDto> userList = new ArrayList<>();
		String selectQuery = "select * from users where USERNAME = ?;";
		System.out.println("test fail");
		
		userList= jdbc.query(selectQuery,
				(rs, rowNum) -> new UserDto(rs.getString("USERNAME")	
							,rs.getString("PASSWORD")
							,(rs.getInt("ENABLED") == 1) ? true:false 
							,null)
				, user);
				
		return userList;
	}
	public void deleteUser(UserDto dto)
	{
		jdbcManager.deleteUser(dto.getUser());	
	}
	public void resetPassward(UserDto dto)
	{
		String resetPasswardQuery = "update users set PASSWORD = ? where USERNAME = ?;";
		jdbc.update(resetPasswardQuery,encoder.encode(dto.getPass()),dto.getUser());
		System.out.println("user passward reset done for : " + dto.getUser() );
	}	
}