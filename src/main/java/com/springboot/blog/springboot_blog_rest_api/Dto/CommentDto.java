package com.springboot.blog.springboot_blog_rest_api.Dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;
}
