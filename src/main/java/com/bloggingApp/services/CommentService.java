package com.bloggingApp.services;

import com.bloggingApp.payloads.CommentDto;

import lombok.Getter;

public interface CommentService {
	
	CommentDto createComment(CommentDto comment,Integer postId);
	void deleteComment(Integer cid);

}
