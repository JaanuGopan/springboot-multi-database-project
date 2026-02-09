package com.jaanu.student_portal.postgres.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String studentName;

    @Column(name = "email", unique = true, nullable = false)
    private String studentEmail;

    @OneToMany(mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ModuleEnrollment> enrollments = new ArrayList<>();

    public Student() {}

    public Student(Long id, String studentName, String studentEmail, List<ModuleEnrollment> enrollments) {
        this.id = id;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.enrollments = enrollments;
    }

    public Student(Long id, String studentName, String studentEmail) {
        this.id = id;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public List<ModuleEnrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<ModuleEnrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
