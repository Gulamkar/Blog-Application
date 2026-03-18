package com.bloggingApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingApp.payloads.ApiResponse;
import com.bloggingApp.payloads.CommentDto;
import com.bloggingApp.services.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/post/{postId}/create")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto , @PathVariable Integer postId){
		CommentDto comment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{cid}")
	public  ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer cid){
		this.commentService.deleteComment(cid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("delete this comment",true), HttpStatus.OK);
	}

}
