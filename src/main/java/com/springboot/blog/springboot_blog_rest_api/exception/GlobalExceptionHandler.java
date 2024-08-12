package com.springboot.blog.springboot_blog_rest_api.exception;

import com.springboot.blog.springboot_blog_rest_api.Dto.ErrorDetailsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    // here we handler our specific error class
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDto> handelTheError(ResourceNotFoundException exception,
                                                          WebRequest webRequest){
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDto, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(CourseError.class)
    public ResponseEntity<ErrorDetailsDto> handelCourseError(CourseError error,
                                                             WebRequest webRequest){
        ErrorDetailsDto errorDetailsDtoCourse = new ErrorDetailsDto(
                new Date(),
                error.getMessage(),
                webRequest.getDescription(false));

                return new ResponseEntity<>(errorDetailsDtoCourse,HttpStatus.NOT_FOUND);

    }
}
