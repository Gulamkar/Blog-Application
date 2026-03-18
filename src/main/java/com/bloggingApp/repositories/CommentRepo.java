package com.bloggingApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingApp.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
