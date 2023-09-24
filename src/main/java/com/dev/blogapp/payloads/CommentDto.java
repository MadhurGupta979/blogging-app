package com.dev.blogapp.payloads;

import com.dev.blogapp.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private int id;
    private String content;
    private Date addedDate;
    private PostDto postDto;
}
