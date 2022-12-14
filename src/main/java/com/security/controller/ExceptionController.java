package com.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import io.jsonwebtoken.JwtException;

@ControllerAdvice
public class ExceptionController 
{
	@ExceptionHandler(value = JwtException.class)
	public ResponseEntity<String>  jwtTokenGetClaims(JwtException e) 
	{
		System.out.println("ExceptionController : JwtException ");
		return new ResponseEntity<String>("Unauthorized User",HttpStatus.UNAUTHORIZED);
	}
}