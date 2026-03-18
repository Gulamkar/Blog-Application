package com.bloggingApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingApp.entity.Category;
import com.bloggingApp.entity.Post;
import com.bloggingApp.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category catergory);

    List<Post> findByPostTitleContaining(String postTitle);

}
