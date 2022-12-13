package com.security.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
public class SecurityConfiguration 
{
	@Autowired
	DataSource dataSource;

	@Autowired
    public void configureAMBuilder(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("jdbc authentication activating..");
        auth.jdbcAuthentication().dataSource(dataSource);
     //       .authoritiesByUsernameQuery("select email, role FROM USERS where email=?")
   //         .usersByUsernameQuery("select email,userPassword, 1 FROM USERS where email=?");
    }
	
}