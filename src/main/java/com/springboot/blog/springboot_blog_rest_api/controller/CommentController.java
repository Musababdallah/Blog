package com.springboot.blog.springboot_blog_rest_api.controller;

import com.springboot.blog.springboot_blog_rest_api.Dto.CommentDto;
import com.springboot.blog.springboot_blog_rest_api.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long postId,
                                                    @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentByPostId(postId);
    }
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "id") long id){
        return ResponseEntity.ok(commentService.getCommentById(id));
    }
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId,
                                                    @PathVariable(value = "commentId") long commentId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(postId,commentId,commentDto);
        return new ResponseEntity<>(updatedComment,HttpStatus.OK);

    }
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId,@PathVariable(value = "commentId") long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("comment delete it ",HttpStatus.OK);

    }
}
