package com.dev.blogapp.services;


import com.dev.blogapp.entities.Comment;
import com.dev.blogapp.entities.Post;
import com.dev.blogapp.entities.User;
import com.dev.blogapp.payloads.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);

    CommentDto updateComment(CommentDto commentDto, Integer commentId);

    List<CommentDto> getAllComments();

    List<CommentDto> getCommentsByPost(Post post);

    CommentDto getCommentById(Integer commentId);
}
