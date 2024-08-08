package com.springboot.blog.springboot_blog_rest_api.service;

import com.springboot.blog.springboot_blog_rest_api.Dto.CourseDto;

import java.util.List;

public interface CourseService {

    CourseDto createCourse(CourseDto courseDto);
    List<CourseDto> getAllCourse();
    CourseDto findById(Long id);
    CourseDto updateCourse(CourseDto courseDto,Long id);
    void deleteCourse(Long id);

}
