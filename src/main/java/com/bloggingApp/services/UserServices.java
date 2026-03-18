package com.bloggingApp.services;


import java.util.List;

import com.bloggingApp.payloads.UserDto;

public interface UserServices {
	
	UserDto createUser (UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById( Integer UserId);
	List<UserDto> getAllUser();
	void deletUser(Integer UserId);
}
