package com.example.Student_Management.service;


import org.springframework.stereotype.Service;

import com.example.Student_Management.dto.StudentRequestDto;
import com.example.Student_Management.dto.StudentResponseDto;
import com.example.Student_Management.exception.ResourceNotFoundException;
import com.example.Student_Management.model.Student;
import com.example.Student_Management.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public StudentResponseDto addStudent(StudentRequestDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());
        Student saved = repository.save(student);

        StudentResponseDto response = new StudentResponseDto();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        response.setCourse(saved.getCourse());
        return response;
    }

    public List<StudentResponseDto> getAllStudents() {
        return repository.findAll().stream().map(student -> {
            StudentResponseDto dto = new StudentResponseDto();
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setEmail(student.getEmail());
            dto.setCourse(student.getCourse());
            return dto;
        }).collect(Collectors.toList());
    }

    public StudentResponseDto getStudentById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        StudentResponseDto dto = new StudentResponseDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setCourse(student.getCourse());
        return dto;
    }

    public StudentResponseDto updateStudent(Long id, StudentRequestDto dto) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());

        Student updated = repository.save(student);

        StudentResponseDto response = new StudentResponseDto();
        response.setId(updated.getId());
        response.setName(updated.getName());
        response.setEmail(updated.getEmail());
        response.setCourse(updated.getCourse());
        return response;
    }

    public void deleteStudent(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        repository.delete(student);
    }
}
