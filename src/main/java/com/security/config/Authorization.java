package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Authorization 
{
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception 
	{
		System.out.println("http authorization set-up");
		http
		.authorizeHttpRequests()
		.requestMatchers("/private").authenticated()
		.requestMatchers("/dashboard").authenticated()
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.and()
		.httpBasic();

		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("password encoder object created");
		return new BCryptPasswordEncoder();
	}
}