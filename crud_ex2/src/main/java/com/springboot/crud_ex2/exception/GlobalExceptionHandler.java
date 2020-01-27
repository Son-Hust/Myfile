package com.springboot.crud_ex2.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException ex, WebRequest request){
        ErrorDetail errorDetail =new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex,WebRequest request){
        ErrorDetail errorDetail= new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDetail,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
