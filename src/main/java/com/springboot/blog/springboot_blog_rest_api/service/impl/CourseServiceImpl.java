package com.springboot.blog.springboot_blog_rest_api.service.impl;

import com.springboot.blog.springboot_blog_rest_api.Dto.CourseDto;
import com.springboot.blog.springboot_blog_rest_api.entity.Course;
import com.springboot.blog.springboot_blog_rest_api.exception.CourseError;
import com.springboot.blog.springboot_blog_rest_api.repository.CourseRepository;
import com.springboot.blog.springboot_blog_rest_api.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDto createCourse(CourseDto courseDto) {

       Course course = mapToEntity(courseDto);
        // Check if a course with the same name already exists
        if (courseRepository.existsByName(course.getName())) {
            throw new CourseError("A course with the name '" + course.getName() + "' already exists.");
        }
       courseRepository.save(course);

       CourseDto myNewCourseDto = mapToDto(course);
       return myNewCourseDto;

    }

    @Override
    public List<CourseDto> getAllCourse() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(course -> mapToDto(course)).collect(Collectors.toList());
    }

    @Override
    public CourseDto findById(Long id) {
        Course coursebyId = courseRepository.findById(id).orElseThrow();
        CourseDto courseId = mapToDto(coursebyId);
        return courseId;
    }

    @Override
    public CourseDto updateCourse(CourseDto courseDto, Long id) {
        Course course = courseRepository.findById(id).orElseThrow();
           course.setName(courseDto.getName());
           course.setLevel(courseDto.getLevel());
           course.setTeacherName(courseDto.getTeacherName());
           course.setPrice(courseDto.getPrice());
           Course updatedCoure = courseRepository.save(course);
           return mapToDto(updatedCoure);


    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow();
        courseRepository.delete(course);
    }



    private Course mapToEntity(CourseDto courseDto){
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setLevel(courseDto.getLevel());
        course.setTeacherName(courseDto.getTeacherName());
        course.setPrice(courseDto.getPrice());
        return course;

    }

    private CourseDto mapToDto(Course course){
        CourseDto newCourse = new CourseDto();
        newCourse.setId(course.getId());
        newCourse.setName(course.getName());
        newCourse.setLevel(course.getLevel());
        newCourse.setTeacherName(course.getTeacherName());
        newCourse.setPrice(course.getPrice());
        return newCourse;

    }
}
