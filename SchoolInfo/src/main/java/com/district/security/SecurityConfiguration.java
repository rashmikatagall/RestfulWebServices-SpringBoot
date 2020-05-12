package com.district.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  
		System.out.println("Configure auth " + auth.toString());
		auth.inMemoryAuthentication()
	        .withUser("rashmi")
	        .password("pwd")
	        .roles("USER")
	        .and()
	        .withUser("admin")
	        .password("pwd")
	        .roles("ADMIN");
	}
	*/
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Configure http " + http.toString());
		http
		    .csrf().disable()   //Only for postman testing
		    .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")            
            .antMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN","USER")
            .and()
            .httpBasic()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}	    
	
}
