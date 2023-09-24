package com.dev.blogapp.controller;

import com.dev.blogapp.payloads.ApiResponse;
import com.dev.blogapp.payloads.CommentDto;
import com.dev.blogapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Integer postId, @RequestBody CommentDto comment) {
        CommentDto savedComment = this.commentService.createComment(comment, postId);
        return ResponseEntity.ok(savedComment);
    }

    @DeleteMapping("comments/delete/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully!!", true), HttpStatus.OK);
    }

    @PutMapping("comments/update/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer commentId, @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = this.commentService.updateComment(commentDto, commentId);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @GetMapping("comments")
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<CommentDto> commentDtos = this.commentService.getAllComments();
        return ResponseEntity.ok(commentDtos);
    }

    @GetMapping("comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Integer commentId) {
        CommentDto commentDto = this.commentService.getCommentById(commentId);
        return ResponseEntity.ok(commentDto);
    }
}
