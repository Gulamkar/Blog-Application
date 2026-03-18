package com.bloggingApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingApp.entity.Comment;
import com.bloggingApp.entity.Post;
import com.bloggingApp.exception.ResourceNotFoundException;
import com.bloggingApp.payloads.CommentDto;
import com.bloggingApp.repositories.CommentRepo;
import com.bloggingApp.repositories.PostRepo;
import com.bloggingApp.services.CommentService;

@Service
public  class CommentServiceImpl implements CommentService {

	@Autowired 
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
    public CommentDto createComment(CommentDto comment, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","id",postId));
		Comment comments=this.modelMapper.map(comment, Comment.class);
		comments.setPost(post);
		Comment saveComment=this.commentRepo.save(comments);
		return this.modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer cid) {
		Comment comment=this.commentRepo.findById(cid).orElseThrow(()->new ResourceNotFoundException("comment","id",cid));
		this.commentRepo.delete(comment);
		
	}

	

}
