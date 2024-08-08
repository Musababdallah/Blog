package com.springboot.blog.springboot_blog_rest_api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "courses" , uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "level",nullable = false)
    private String level;
    @Column(name = "teacherName")
    private String teacherName;
    @Column(name = "price",nullable = false)
    private Integer price;
}
