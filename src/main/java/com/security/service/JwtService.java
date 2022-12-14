package com.security.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	private String SECRET_KEY = "Generate JWT Using This Key";
	private double EXPIRATION_TIME = 24 * 60 * 60 * 1000;
	Map<String, Object> map = new HashMap<>();
	
	public String jwtTokenGenerator(Object object) {
		System.out.println(object);
		String jwt = Jwts.builder()
				.setClaims(map)
				.setSubject(object.toString())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date((long) (System.currentTimeMillis() + EXPIRATION_TIME)))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
		System.out.println("jwt : " + jwt);
		return jwt;
	}

	public boolean jwtTokenValidator(String token) 
	{
		boolean isValid;
		try 
		{
		Claims claims = jwtTokenGetClaims(token);
		isValid = false;
		System.out.println("jwt is valid");
		System.out.println("claims : " + claims.getSubject());
		System.out.println("validate till : " + claims.getExpiration());
		System.out.println(" 			is before ");
		System.out.println("current time  : " + new Date());
		System.out.println("               ---------------------------");
		}
		catch (JwtException e) 
		{
			isValid = true;
			System.out.println("JwtException : jwt is not valid");
		}
		catch(Exception e)
		{
			isValid = true;
			System.out.println("Exception : jwt is not valid");
		}
		System.out.println("isValid : " + !isValid);
		return isValid;
	}
	public Claims jwtTokenGetClaims(String token) 
	{
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}