package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.security.custom.anotation.JWtToken;
import com.security.custom.anotation.ValidateJwtToken;
import com.security.service.JwtService;
import io.jsonwebtoken.JwtException;

/*
 * 
 */

@RestController
@RequestMapping("/api")
public class RequestHandler 
{
	@Autowired
	JwtService service;
	@GetMapping("/")
	public void name() 
	{
		throw new JwtException("");
//		return "default";
	}
	@GetMapping("/home")
	public String home() 
	{
		return "home";
	}
	@GetMapping("/login")
	public String login() 
	{
		/*	
		Get the information of the logged in user : getPrincipal()
		Get the password of the authenticated user: getCredentials()
		Get the assigned roles of the authenticated user: getAuthorities()
		Get further details of the authenticated user: getDetails()
		 */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		System.out.println("getDetails : " +  auth.getDetails().toString());
		System.out.println("getCredentials : " +  auth.getCredentials());
		System.out.println("getPrincipal : " +  auth.getPrincipal().toString());
		System.out.println("getAuthorities : " +  auth.getAuthorities().toString());
		return service.jwtTokenGenerator(auth.getPrincipal());
	}
	@GetMapping("/dashboard")
	@ValidateJwtToken
	public ResponseEntity<String> dashboard(@JWtToken @RequestParam("authorization") String token) 
	{
		System.out.println("dashboard request received...");
		try
		{
			
		}
		catch(JwtException e) 
		{
			System.out.println("JwtException : dashboard ");
			return new ResponseEntity<String>("Unauthorized User",HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("Authorized User",HttpStatus.OK);
	}
	@GetMapping("/private")
	@ValidateJwtToken
	public String privateApi(@JWtToken() @RequestParam("authorization") String token)
	{
		return "private";
	}
}