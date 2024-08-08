package com.springboot.blog.springboot_blog_rest_api.service.impl;

import com.springboot.blog.springboot_blog_rest_api.Dto.PostDto;
import com.springboot.blog.springboot_blog_rest_api.Dto.PostResponse;
import com.springboot.blog.springboot_blog_rest_api.entity.Post;
import com.springboot.blog.springboot_blog_rest_api.exception.ResourceNotFoundException;
import com.springboot.blog.springboot_blog_rest_api.repository.PostRepository;
import com.springboot.blog.springboot_blog_rest_api.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository; //to access for save, saveall,find,etc

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert DTO to entity
        Post post = mapToEntity(postDto);
        // save operation
        Post newPost = postRepository.save(post);
        // convert entity to DTO
        PostDto postResponse = mapToDto(newPost);
        return postResponse;

    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize) {
        // create pageable instance
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        //List<Post> posts = postRepository.findAll();// pageable return Page not List so we chane list if we use pageable


        Page<Post> posts = postRepository.findAll(pageable);//will back with entity result

        //get content for page object
        List<Post> listOfPosts = posts.getContent();

        // we have to convert entity result to dto
        //return listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        //for pagenation client must see no page and content and see last element all element
        List<PostDto> contents = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        // make object for PostResponse

        PostResponse postRespons = new PostResponse();
        postRespons.setContent(contents);
        postRespons.setPageNo(posts.getNumber());
        postRespons.setPageSize(posts.getSize());
        postRespons.setTotalElement(posts.getTotalElements());
        postRespons.setTotalPages(posts.getTotalPages());
        postRespons.setLast(posts.isLast());
        return postRespons;

        //_____________________ Without pagneation___________//

        //  List<Post> posts = postRepository.findAll();
        // return post.stream().map(post->mapToDto(post)).collect(collectors.toList());


        //______________________________________________//
    }


    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        //we have to create it to dto after we find it
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        // get post by id from database (entity)
        //and first we have to check if post exit too
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        postRepository.delete(post);
    }

    // convert entity to Dto
    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;

    }

    // convert Dto to entity
    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
