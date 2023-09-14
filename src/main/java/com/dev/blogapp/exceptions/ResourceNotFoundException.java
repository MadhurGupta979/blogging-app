package com.dev.blogapp.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private long fieldVal;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldVal) {
        super(String.format("%s not found with %s : %l", resourceName, fieldName, fieldVal));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldVal = fieldVal;
    }


}
