package com.security.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.security.dto.UserDto;
import com.security.dto.UserPwdResetDto;
import com.security.service.UserService;

@RestController
@RequestMapping("/user/")
public class UserHandler 
{
	@Autowired
	private UserService service;
	
	@Autowired
	private PasswordEncoder encoder;
	/*
	 * create user :
	 */
	@GetMapping("create")
	public ResponseEntity<String> createUser(@RequestBody UserDto dto) 
	{
		System.out.println("new user request received...");
		service.createUser(dto);
		if(service.selectUser(dto.getUser()).size() > 0)
			return new ResponseEntity<>("user created",HttpStatus.OK);		
		return new ResponseEntity<>("fail",HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	/*
	 * reset passward
	 * 
	 * old_pass = passward from db
	 * if matches then update pwd with new_pass in db
	 */
	@GetMapping("resetpassward")
	public ResponseEntity<String> resetUserPassward(@RequestBody UserPwdResetDto dto) 
	{
		System.out.println("reset user passward request received...");
		List<UserDto> selectUser = service.selectUser(dto.getUser());
		UserDto user = selectUser.get(0);
		user.toString();
		String pass = (selectUser.get(0).getPass());
		//match old_pass with passward stored in db
		boolean matches = encoder.matches(dto.getOld_pass(), pass);
		System.out.println("passward match : " + matches);
		//if matches then update passward in db
		if(matches)
		{
			user.setPass(dto.getNew_pass());
			service.resetPassward(user);
			System.out.println("passward reset done");
			return new ResponseEntity<>("passward reset done",HttpStatus.OK);				
		}
		//not match then send msg
		return new ResponseEntity<>("invalid old passward",HttpStatus.OK);	
	}
	/*
	 * delete user :
	 */
	@GetMapping("delete")
	public ResponseEntity<String> deleteUser(@RequestBody UserDto dto) 
	{
		System.out.println("delete user request received...");
		service.deleteUser(dto);
		return new ResponseEntity<>("user deleted",HttpStatus.OK);		
		
	}
}