package com.bloggingApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bloggingApp.security.CustomerUserDetailService;
import com.bloggingApp.security.JwtAuthenticationEntryPoint;
import com.bloggingApp.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
	
	 @Autowired
	 private CustomerUserDetailService customerUserDetailService;
	 
	 @Autowired
	 private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	 
	 @Autowired 
	 private JwtAuthenticationFilter jwtAuthenticationFilter;
	 
	 @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		  http
          .csrf(csrf -> csrf.disable())
          .exceptionHandling(ex -> ex
              .authenticationEntryPoint(jwtAuthenticationEntryPoint)
          )
          .sessionManagement(session -> session
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          )
          .authorizeHttpRequests(auth -> auth
        		    .requestMatchers("/api/v1/auth/login").permitAll()
        		    .requestMatchers(HttpMethod.GET, "/api/users/**").permitAll()
        		    .requestMatchers(HttpMethod.POST, "/api/users/**").permitAll()
        		    .requestMatchers(HttpMethod.PUT, "/api/users/**").permitAll()
        		    .requestMatchers(HttpMethod.DELETE, "/api/users/**").permitAll()
        		    .anyRequest().authenticated()
        		

          );

      // ✅ JWT filter BEFORE username/password filter
      http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

      return http.build();
		   
	  }
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.customerUserDetailService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
	        AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}
	

}
