package com.bloggingApp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bloggingApp.entity.User;
import com.bloggingApp.exception.ResourceNotFoundException;
import com.bloggingApp.payloads.UserDto;
import com.bloggingApp.repositories.UserRepo;
import com.bloggingApp.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
	    User user = this.dtoToUser(userDto);

	    // ✅ ENCODE PASSWORD BEFORE SAVING
	    user.setPassword(passwordEncoder.encode(userDto.getPassword()));

	    User savedUser = this.userRepo.save(user);
	    return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
	    User user = this.userRepo.findById(userId)
	        .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

	    user.setName(userDto.getName());
	    user.setEmail(userDto.getEmail());

	    // ✅ ENCODE UPDATED PASSWORD
	    user.setPassword(passwordEncoder.encode(userDto.getPassword()));

	    user.setAbout(userDto.getAbout());

	    User updatedUser = this.userRepo.save(user);
	    return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users= this.userRepo.findAll();
		List<UserDto> userDto=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deletUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
		this.userRepo.delete(user);
		

	}
	
	public User dtoToUser(UserDto userDto) {
//		User user=new User();
//		user.setID(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		User user=this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	public UserDto userToDto(User user) {
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getID());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		return userDto;
		
	}

}
