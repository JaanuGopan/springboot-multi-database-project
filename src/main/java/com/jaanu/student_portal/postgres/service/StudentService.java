package com.jaanu.student_portal.postgres.service;

import com.jaanu.student_portal.mongo.document.ActivityType;
import com.jaanu.student_portal.mongo.service.ActivityLogService;
import com.jaanu.student_portal.postgres.entity.Student;
import com.jaanu.student_portal.postgres.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ActivityLogService activityLogService;

    public StudentService(StudentRepository studentRepository, ActivityLogService activityLogService) {
        this.studentRepository = studentRepository;
        this.activityLogService = activityLogService;
    }

    @Transactional
    public Student createStudent(Student student) {
        Student saved = studentRepository.save(student);

        activityLogService.createLog(
            saved.getId(),
            ActivityType.CREATE_STUDENT,
            Map.of(
                    "studentName", saved.getStudentName(),
                    "studentEmail", saved.getStudentEmail()
            )
        );

        return saved;
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Student student) {
        Student savedStudent = studentRepository.findById(student.getId()).orElseThrow(
                () -> new IllegalArgumentException("Student with id " + student.getId() + " not found")
        );

        savedStudent.setStudentName(student.getStudentName());
        savedStudent.setStudentEmail(student.getStudentEmail());

        activityLogService.createLog(
                savedStudent.getId(),
                ActivityType.UPDATE_STUDENT,
                Map.of(
                        "studentName", savedStudent.getStudentName(),
                        "studentEmail", savedStudent.getStudentEmail()
                )
        );

        return studentRepository.save(savedStudent);
    }


    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalArgumentException("Student with id " + studentId + " not found")
        );
        studentRepository.delete(student);

        activityLogService.createLog(
                student.getId(),
                ActivityType.DELETE_STUDENT,
                Map.of(
                        "studentName", student.getStudentName(),
                        "studentEmail", student.getStudentEmail()
                )
        );
    }

}
