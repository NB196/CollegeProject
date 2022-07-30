package com.lastmin.LastMin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth ) throws Exception{
		auth
			.inMemoryAuthentication() 
			.withUser("Jane").password(passwordEncoder().encode("jane123")).roles("beautician")
			.and()
			.withUser("Ria").password(passwordEncoder().encode("Ria123")).roles("client");
			
	}
	
	//open incognito window for this- it remembers you otherwise
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
			.antMatchers("/filter/{id}").hasAnyRole("beautician", "client")
			.antMatchers("/create").hasRole("beautician")
			.antMatchers("/delete/{id}").hasRole("beautician")
			.antMatchers("/available").hasAnyRole("beautician", "client")
			.antMatchers("/bookings").hasRole("beautician")
			
			//.anyRequest().authenticated() //does not matter what request the user makes, they first need to be authenticated
			.and()
			.httpBasic(); //use http authentication for this purpose
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
