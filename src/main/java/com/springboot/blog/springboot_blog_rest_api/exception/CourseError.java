package com.springboot.blog.springboot_blog_rest_api.exception;

public class CourseError extends RuntimeException{

    public CourseError(String message) {
        super(message);
    }
}
