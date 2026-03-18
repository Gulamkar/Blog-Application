package com.bloggingApp.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.bloggingApp.entity.Comment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;
	@NotNull
	private String postTitle;
	@NotNull
	private String postContent;
	
	private Date addedDate;
	
	private String imageName;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private Set<CommentDto>comment=new HashSet<>();

}
