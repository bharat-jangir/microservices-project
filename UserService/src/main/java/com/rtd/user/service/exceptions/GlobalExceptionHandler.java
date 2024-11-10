package com.rtd.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rtd.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
        String message=ex.getMessage();
        ApiResponse apiResponse = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }
}
