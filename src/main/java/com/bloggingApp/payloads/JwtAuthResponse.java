package com.bloggingApp.payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {
	
	private UserDto user;
	
	private String token;
}
