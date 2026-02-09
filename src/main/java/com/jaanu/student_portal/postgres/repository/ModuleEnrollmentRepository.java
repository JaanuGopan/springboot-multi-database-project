package com.jaanu.student_portal.postgres.repository;

import com.jaanu.student_portal.postgres.entity.ModuleEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleEnrollmentRepository extends JpaRepository<ModuleEnrollment, Long> {
    boolean existsByStudentIdAndModuleId(Long studentId, Long moduleId);

}
