package com.dev.blogapp.payloads;

import com.dev.blogapp.entities.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDto {
    private int id;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto categoryDto;
    private UserDto userDto;
}
