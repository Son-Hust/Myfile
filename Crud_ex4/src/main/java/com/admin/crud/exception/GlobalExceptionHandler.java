package com.admin.crud.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {


    //Handle Exception Not Found
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> globalHandler(NotFoundException ex){
        ErrorDetail errorDetail=new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource not Found!");
        errorDetail.setDeveloperMessage(ex.getClass().getName());
        return new ResponseEntity<>(errorDetail,null,HttpStatus.NOT_FOUND);

    }

    //Handle Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerValidationError(MethodArgumentNotValidException ex, HttpServletRequest request){
        ErrorDetail errorDetail=new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setDetail("Input Validation Failed!");
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTitle("Validation Failed!");
        errorDetail.setDeveloperMessage(ex.getClass().getName());


        //Create Validation Instance
        List<FieldError> fieldErrors=ex.getBindingResult().getFieldErrors();
        for (FieldError fe:fieldErrors) {
            List<ValidationError> validationErrorsList=errorDetail.getError().get(fe.getField());
            if(validationErrorsList == null) {
                validationErrorsList = new ArrayList<ValidationError>();
                errorDetail.getError().put(fe.getField(),
                        validationErrorsList);
            }
            ValidationError validationError=new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(fe.getDefaultMessage());
            validationErrorsList.add(validationError);
        }
        return new ResponseEntity<>(errorDetail,null,HttpStatus.BAD_REQUEST);
    }
}
