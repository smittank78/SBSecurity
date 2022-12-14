package com.security.inbuilt.service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.security.service.JwtService;
import io.jsonwebtoken.JwtException;

/*
 * this is aop api providing advice for methods
 */

@Component
@Aspect
public class Aop 
{	
	@Autowired
	JwtService service;
	
	@Pointcut("@annotation(com.security.custom.anotation.ValidateJwtToken) && args(token)") //annotation path
	public void Jwt(String token) {
	}
	
	@Before(value = "Jwt(token)" , argNames = "token")
	public void validateJWt(String token)
	{
		System.out.println("before : validate token : " +  token);
		if(service.jwtTokenValidator(token.toString()))
		{
			System.out.println("token invalid redirect to login");
			throw new JwtException("unauthorized user");
		}
		else
		{
			System.out.println("token validated");
		}
	}
}