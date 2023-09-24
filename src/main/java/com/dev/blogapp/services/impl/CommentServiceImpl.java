package com.dev.blogapp.services.impl;

import com.dev.blogapp.entities.Category;
import com.dev.blogapp.entities.Comment;
import com.dev.blogapp.entities.Post;
import com.dev.blogapp.exceptions.ResourceNotFoundException;
import com.dev.blogapp.payloads.CategoryDto;
import com.dev.blogapp.payloads.CommentDto;
import com.dev.blogapp.payloads.PostDto;
import com.dev.blogapp.payloads.UserDto;
import com.dev.blogapp.repository.CommentRepo;
import com.dev.blogapp.repository.PostRepo;
import com.dev.blogapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        commentDto.setAddedDate(new Date());
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        post.getComments().add(comment);

        Comment savedComment = this.commentRepo.save(comment);
        CommentDto commentDto1 = this.modelMapper.map(savedComment, CommentDto.class);
        commentDto1.setPostDto(this.modelMapper.map(post, PostDto.class));
        commentDto1.getPostDto().setUserDto(this.modelMapper.map(post.getUser(), UserDto.class));
        commentDto1.getPostDto().setCategoryDto(this.modelMapper.map(post.getCategory(), CategoryDto.class));

        return commentDto1;
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
        this.commentRepo.delete(comment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
        comment.setContent(commentDto.getContent());

        Comment savedComment = this.commentRepo.save(comment);
        CommentDto commentDto1 = this.modelMapper.map(savedComment, CommentDto.class);
        commentDto1.setPostDto(this.modelMapper.map(savedComment.getPost(), PostDto.class));
        commentDto1.getPostDto().setUserDto(this.modelMapper.map(savedComment.getPost().getUser(), UserDto.class));
        commentDto1.getPostDto().setCategoryDto(this.modelMapper.map(savedComment.getPost().getCategory(), CategoryDto.class));

        return commentDto1;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = this.commentRepo.findAll();
        List<CommentDto> commentDtos = comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
        for (int i = 0; i < comments.size(); i++) {
            commentDtos.get(i).setPostDto(this.modelMapper.map(comments.get(i).getPost(), PostDto.class));
            commentDtos.get(i).getPostDto().setUserDto(this.modelMapper.map(comments.get(i).getPost().getUser(), UserDto.class));
            commentDtos.get(i).getPostDto().setCategoryDto(this.modelMapper.map(comments.get(i).getPost().getCategory(), CategoryDto.class));
        }

        return commentDtos;
    }

    @Override
    public List<CommentDto> getCommentsByPost(Post post) {
        List<Comment> comments = this.commentRepo.findByPost(post);
        List<CommentDto> commentDtos = comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
        for (int i = 0; i < comments.size(); i++) {
            commentDtos.get(i).setPostDto(this.modelMapper.map(comments.get(i).getPost(), PostDto.class));
            commentDtos.get(i).getPostDto().setUserDto(this.modelMapper.map(comments.get(i).getPost().getUser(), UserDto.class));
            commentDtos.get(i).getPostDto().setCategoryDto(this.modelMapper.map(comments.get(i).getPost().getCategory(), CategoryDto.class));
        }

        return commentDtos;
    }

    @Override
    public CommentDto getCommentById(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
        CommentDto commentDto = this.modelMapper.map(comment, CommentDto.class);
        commentDto.setPostDto(this.modelMapper.map(comment.getPost(), PostDto.class));
        commentDto.getPostDto().setUserDto(this.modelMapper.map(comment.getPost().getUser(), UserDto.class));
        commentDto.getPostDto().setCategoryDto(this.modelMapper.map(comment.getPost().getCategory(), CategoryDto.class));
        return commentDto;
    }
}


