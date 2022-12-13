package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Import(SecurityConfiguration.class)
public class InMemoryAuthentication 
{		
	@Autowired
	PasswordEncoder passwordEncoder;
	/*
	 * In Memory Authentication and User Creation
	 */
/*	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
		
        UserDetails user = User
        	.withUsername("user")
            .password("user1")
            .roles("user")
            .passwordEncoder((pass)-> passwordEncoder.encode(pass) )
            .build();
        
        UserDetails admin = User
            	.withUsername("admin")
                .password("admin1")
                .roles("admin")
                .passwordEncoder((pass)-> passwordEncoder().encode(pass) )
                .build();
        
        List<UserDetails> userDetails = Arrays.asList(user,admin);
        		
        System.out.println("users created : " + user.getUsername() + " - " + user.getPassword());
        System.out.println("admin created : " + admin.getUsername() + " - " + admin.getPassword());
        
        return new InMemoryUserDetailsManager(userDetails);
    }
*/
}