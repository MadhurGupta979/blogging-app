package com.dev.blogapp.payloads;

import lombok.*;
import org.hibernate.annotations.CollectionId;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDto {
    private int id;

    private String title;

    private String description;
}
