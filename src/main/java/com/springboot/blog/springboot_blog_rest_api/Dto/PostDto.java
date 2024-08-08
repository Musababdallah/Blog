package com.springboot.blog.springboot_blog_rest_api.Dto;

import lombok.Data;

@Data
public class PostDto { //this transfer from java object to json for client

    private long id;
    private String title;
    private String description;
    private String content;
}
