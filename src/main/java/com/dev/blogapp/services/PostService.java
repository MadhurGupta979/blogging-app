package com.dev.blogapp.services;

import com.dev.blogapp.entities.Post;
import com.dev.blogapp.payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

    List<PostDto> getAllPosts();

    PostDto getPostById(Integer postId);
}
