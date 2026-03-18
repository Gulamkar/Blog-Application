package com.bloggingApp.services;

import java.util.List;

import com.bloggingApp.entity.Post;
import com.bloggingApp.payloads.PostDto;
import com.bloggingApp.payloads.PostResponse;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//getAll Post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	// get by post Id
	PostDto getByPostId(Integer postId);
	
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all post by user
	List<PostDto> getPostByUser(Integer userId);
	
	//search post
	List<PostDto>searchPosts(String keyword);

}
