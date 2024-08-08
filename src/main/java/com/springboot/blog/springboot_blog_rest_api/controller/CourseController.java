package com.springboot.blog.springboot_blog_rest_api.controller;

import com.springboot.blog.springboot_blog_rest_api.Dto.CourseDto;
import com.springboot.blog.springboot_blog_rest_api.repository.CourseRepository;
import com.springboot.blog.springboot_blog_rest_api.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")

public class CourseController {
        private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping
    public List<CourseDto> getAllCourse(){
        return courseService.getAllCourse();
    }

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto){
        return ResponseEntity.ok(courseService.createCourse(courseDto));

        //return new ResponseEntity<>(courseService.createCourse(courseDto), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok(courseService.findById(id));

    }
    @PutMapping("{id}")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto ,@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(courseService.updateCourse(courseDto,id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable(name = "id") Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.ok("delete it is done");
    }


}
