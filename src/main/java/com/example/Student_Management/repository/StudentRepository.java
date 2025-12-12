package com.example.Student_Management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Student_Management.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
