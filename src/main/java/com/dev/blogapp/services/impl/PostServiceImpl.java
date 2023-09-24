package com.dev.blogapp.services.impl;

import com.dev.blogapp.entities.Category;
import com.dev.blogapp.entities.Post;
import com.dev.blogapp.entities.User;
import com.dev.blogapp.exceptions.ResourceNotFoundException;
import com.dev.blogapp.payloads.CategoryDto;
import com.dev.blogapp.payloads.PostDto;
import com.dev.blogapp.payloads.UserDto;
import com.dev.blogapp.repository.CategoryRepo;
import com.dev.blogapp.repository.PostRepo;
import com.dev.blogapp.repository.UserRepo;
import com.dev.blogapp.services.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

        postDto.setImageName("default.png");
        postDto.setAddedDate(new Date());

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setUser(user);
        post.setCategory(category);
        category.getPosts().add(post);
        user.getPosts().add(post);

        Post savedPost = this.postRepo.save(post);

        PostDto postDto1 = this.modelMapper.map(savedPost, PostDto.class);
        postDto1.setUserDto(this.modelMapper.map(savedPost.getUser(), UserDto.class));
        postDto1.setCategoryDto(this.modelMapper.map(savedPost.getCategory(), CategoryDto.class));

        return postDto1;
    }


    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "post id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post savedPost = this.postRepo.save(post);
        PostDto postDto1 = this.modelMapper.map(savedPost, PostDto.class);
        postDto1.setCategoryDto(this.modelMapper.map(savedPost.getCategory(), CategoryDto.class));
        postDto.setUserDto(this.modelMapper.map(savedPost.getUser(), UserDto.class));
        return postDto;
    }


    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        this.postRepo.delete(post);
    }


    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        for (int i = 0; i < posts.size(); i++) {
            postDtos.get(i).setCategoryDto(this.modelMapper.map(cat, CategoryDto.class));
            postDtos.get(i).setUserDto(this.modelMapper.map(posts.get(i).getUser(), UserDto.class));
        }

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        for (int i = 0; i < posts.size(); i++) {
            postDtos.get(i).setCategoryDto(this.modelMapper.map(posts.get(i).getCategory(), CategoryDto.class));
            postDtos.get(i).setUserDto(this.modelMapper.map(user, UserDto.class));
        }

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        for (int i = 0; i < posts.size(); i++) {
            postDtos.get(i).setCategoryDto(this.modelMapper.map(posts.get(i).getCategory(), CategoryDto.class));
            postDtos.get(i).setUserDto(this.modelMapper.map(posts.get(i).getUser(), UserDto.class));
        }

        return postDtos;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDtos = posts.stream().map(p -> this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
        for (int i = 0; i < posts.size(); i++) {
            postDtos.get(i).setCategoryDto(this.modelMapper.map(posts.get(i).getCategory(), CategoryDto.class));
            postDtos.get(i).setUserDto(this.modelMapper.map(posts.get(i).getUser(), UserDto.class));
        }
        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        postDto.setUserDto(this.modelMapper.map(post.getUser(), UserDto.class));
        postDto.setCategoryDto(this.modelMapper.map(post.getCategory(), CategoryDto.class));
        return postDto;
    }
}
