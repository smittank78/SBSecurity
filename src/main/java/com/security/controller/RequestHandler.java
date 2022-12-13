package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestHandler 
{
	@GetMapping("/")
	public String name() 
	{
		return "default";
	}
	@GetMapping("/home")
	public String home() 
	{
		return "home";
	}
	@GetMapping("/dashboard")
	public String dashboard() 
	{
		return "dashboard";
	}
	@GetMapping("/private")
	public String privateApi() 
	{
		return "private";
	}
}