package com.springboot.blog.springboot_blog_rest_api.Dto;


import java.util.Date;

public class ErrorDetailsDto {
    private Date timeForError;
    private String message;
    private String details;

    public ErrorDetailsDto(Date timeForError, String message, String details) {
        this.timeForError = timeForError;
        this.message = message;
        this.details = details;
    }

    public Date getTimeForError() {
        return timeForError;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
