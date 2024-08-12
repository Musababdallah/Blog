package com.springboot.blog.springboot_blog_rest_api.service.impl;

import com.springboot.blog.springboot_blog_rest_api.Dto.CommentDto;
import com.springboot.blog.springboot_blog_rest_api.SpringbootBlogRestApiApplication;
import com.springboot.blog.springboot_blog_rest_api.entity.Comment;
import com.springboot.blog.springboot_blog_rest_api.entity.Post;
import com.springboot.blog.springboot_blog_rest_api.exception.BlogException;
import com.springboot.blog.springboot_blog_rest_api.exception.ResourceNotFoundException;
import com.springboot.blog.springboot_blog_rest_api.repository.CommentRepository;
import com.springboot.blog.springboot_blog_rest_api.repository.PostRepository;
import com.springboot.blog.springboot_blog_rest_api.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        // map to entity
        Comment comment = mapToEntity(commentDto);
        // retrive Post entity by Id to Set it in postId in comment
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId));
        // here we set it
        comment.setPost(post);
        //last we have to save the comment
        commentRepository.save(comment);
        // return
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentByPostId(Long postId) {
        //retrieve comment by postId by repository
        List<Comment> comments = commentRepository.findByPostId(postId);
        // convert the list from entity to Dto using stream
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) {
       Comment comment = checkCommentBelong(postId,commentId);

        // set the data from commentRequest that client send it
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        // we have to save it and return it to entity
        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);

    }
    private Comment checkCommentBelong(long postId,long commentId){
        // retrieve post entity from db by id
        Post post  = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));

        // retrieve comment from db by id
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }
        return comment;
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Comment comment = checkCommentBelong(postId,commentId);

        commentRepository.delete(comment);

    }

    private Comment mapToEntity( CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto,Comment.class);
      /*  Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());*/
        return comment;

    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
       /* CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());*/
        return commentDto;
    }
}
