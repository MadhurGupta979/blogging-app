package com.dev.blogapp.repository;

import com.dev.blogapp.entities.Category;
import com.dev.blogapp.entities.Post;
import com.dev.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);


    List<Post> findByTitleContaining(String query);
}
