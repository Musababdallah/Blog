package com.springboot.blog.springboot_blog_rest_api.service;

import com.springboot.blog.springboot_blog_rest_api.Dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long id, CommentDto commentDto);
    List<CommentDto> getCommentByPostId(Long postId);
    CommentDto getCommentById(Long commentId);

    CommentDto updateComment(long postId, long commentId, CommentDto commentRequest);
    void deleteComment(long postId,long commentId);
}