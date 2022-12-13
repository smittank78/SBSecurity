package com.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
@Import(SecurityConfiguration.class)
@PropertySource("classpath:jdbc.properties")
public class JdbcAuthentication 
{
	@Autowired
	Environment environment;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Bean
	DataSource dataSource()
	{
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 dataSource.setUrl(environment.getProperty("datasource.url"));
		 dataSource.setDriverClassName(environment.getProperty("datasource.driver"));
		 dataSource.setUsername(environment.getProperty("datasource.username"));
		 dataSource.setPassword(environment.getProperty("datasource.password"));
		 System.out.println("data-source object created");
		 return dataSource;
	}
	@Bean
	JdbcTemplate jdbcTemplate()
	{
		System.out.println("JdbcTemplate object created");
		return new JdbcTemplate(dataSource());
	}
	@Bean
	JdbcUserDetailsManager jdbcUserDetailsManager()
	{
		System.out.println("JdbcUserDetailsManager object created");
		return new JdbcUserDetailsManager(dataSource());
	}
	
/*	create user inh db when application executed
 *
 	@Bean
	public UserDetailsManager users(DataSource dataSource) 
	{
		System.out.println("jdbc user creating");
	    UserDetails user = User
	          	.withUsername("user1")
	            .password("user123")
	            .roles("user")
	            .passwordEncoder((pass)-> passwordEncoder.encode(pass) )
	            .build();
	      
	      JdbcUserDetailsManager manager = jdbcUserDetailsManager();
	      manager.createUser(user);

	      return manager;
	    }
*/	
}