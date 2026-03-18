package com.bloggingApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingApp.payloads.JwtAuthRequest;
import com.bloggingApp.payloads.JwtAuthResponse;
import com.bloggingApp.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")

public class AuthController {
	
	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private JwtTokenHelper jwtTokenHelper;

	    @PostMapping("/login")
	    public ResponseEntity<JwtAuthResponse> login(
	            @RequestBody JwtAuthRequest request) throws Exception {

	        authenticate(request.getUsername(), request.getPassword());

	        UserDetails userDetails =
	                userDetailsService.loadUserByUsername(request.getUsername());

	        String token = jwtTokenHelper.generateToken(userDetails);

	        JwtAuthResponse response = new JwtAuthResponse();
	        response.setToken(token);

	        return ResponseEntity.ok(response);
	    }

	    private void authenticate(String username, String password) throws Exception {
	        
	            UsernamePasswordAuthenticationToken authenticationToken =
	                    new UsernamePasswordAuthenticationToken(username, password);
            try {
            	
	            this.authenticationManager.authenticate(authenticationToken);

	        } catch (BadCredentialsException e) {
	            throw new Exception("Invalid username or password");
	        }
	    }

}
