package com.springboot.blog.springboot_blog_rest_api.exception;

import org.springframework.http.HttpStatus;

public class BlogException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public BlogException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
