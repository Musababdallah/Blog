package com.springboot.blog.springboot_blog_rest_api.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private String level;
    private String teacherName;
    private Integer price;
}
