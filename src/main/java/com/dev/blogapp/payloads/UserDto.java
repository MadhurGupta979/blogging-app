package com.dev.blogapp.payloads;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
