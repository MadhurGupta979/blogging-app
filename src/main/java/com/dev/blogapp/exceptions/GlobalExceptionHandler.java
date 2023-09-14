package com.dev.blogapp.exceptions;

import com.dev.blogapp.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleException(ResourceNotFoundException exc) {
        String message = exc.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

}
