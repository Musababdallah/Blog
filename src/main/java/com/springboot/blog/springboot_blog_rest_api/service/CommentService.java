package com.springboot.blog.springboot_blog_rest_api.service;

import com.springboot.blog.springboot_blog_rest_api.Dto.CommentDto;

public interface CommentService {
    CommentDto createComment(Long id, CommentDto commentDto);
}
