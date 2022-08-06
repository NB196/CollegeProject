//package com.lastmin.LastMin;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth ) throws Exception{
//		auth
//			.inMemoryAuthentication() 
//			.withUser("Jane").password(passwordEncoder().encode("jane123")).roles("beautician")
//			.and()
//			.withUser("Ria").password(passwordEncoder().encode("Ria123")).roles("client");
//			
//	}
//	
//	//open incognito window for this- it remembers you otherwise
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http
//			.authorizeRequests()
//			.antMatchers("/appointments/filter/{id}").hasAnyRole("beautician", "client") //find app by id
//			.antMatchers("/appointments/create").hasRole("beautician") //create an app
//			.antMatchers("/appointments/delete/{id}").hasRole("beautician") //beautician cancels app
//			.antMatchers("/appointments/available").hasAnyRole("beautician", "client") //apps that have not been booked on system
//			.antMatchers("/appointments/bookings").hasRole("beautician") //beauticians can see the booked apps
//			//.antMatchers("/appointments/modify/date/{id}").hasAnyRole("beautician", "client") //book an app
//			.antMatchers("/appointments/modify/time/{id}").hasAnyRole("beautician", "client")
//			.antMatchers("/appointments/modify/cancel/{id}").hasRole("client") ////client cancel app by updating bookedInd by using appointment id 
//			.antMatchers("/appointments/modify/book/{id}").hasRole("client") // client book appointment by app id, by updating the bookingInd to Y
//			.and()
//			.httpBasic(); //use http authentication for this purpose
//	}
//	
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//}
