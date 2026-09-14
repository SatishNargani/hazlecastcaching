package com.cache.demo.controller;

import com.cache.demo.entity.Student;
import com.cache.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@Slf4j
@CacheConfig(cacheNames = "students")
public class StudentController {
    @Autowired
    private StudentService service;
    @GetMapping
    @Cacheable(key= "#id")
    public List<Student> getAllStudents()
    {
       return service.getAllStudents();
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student)
    {
        return service.createStudent(student);

    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id)
    {
        log.info("fetching the student with id" + id +"from DB");
        return service.getStudentById(id);
    }
    @PutMapping("/{id}")
    @CachePut(key = "#id")
    public Student updateStudent(@RequestBody Student student, @PathVariable Integer id)
    {
        return service.updateStudent(student,id);
    }
    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id")
    public Student deleteStudent(@PathVariable Integer id)
    {
        return service.deleteStudent(id);
    }
}
