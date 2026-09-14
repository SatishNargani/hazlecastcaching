package com.cache.demo.service.impl;

import com.cache.demo.Repository.StudentRepository;
import com.cache.demo.entity.Student;
import com.cache.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepo;

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentRepo.findById(studentId).orElse(null);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Student updateStudent(Student student, Integer studentId) {
        Student studentt =  studentRepo.findById(studentId).orElse(null);
        studentt.setFirstName(student.getFirstName());
        studentt.setLastName(student.getLastName());
        studentt.setAge(student.getAge());
        return studentRepo.save(studentt);

    }

    @Override
    public Student deleteStudent(Integer studentId) {
        Student studentt =  studentRepo.findById(studentId).orElse(null);
        studentRepo.deleteById(studentId);
        return studentt;
    }
}
