package com.jaanu.student_portal.postgres.repository;

import com.jaanu.student_portal.postgres.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByStudentEmail(String studentEmail);
    Student findByStudentName(String studentName);
}
