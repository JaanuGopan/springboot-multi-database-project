package com.jaanu.student_portal.postgres.service;

import com.jaanu.student_portal.mongo.document.ActivityType;
import com.jaanu.student_portal.mongo.service.ActivityLogService;
import com.jaanu.student_portal.postgres.entity.Module;
import com.jaanu.student_portal.postgres.entity.ModuleEnrollment;
import com.jaanu.student_portal.postgres.entity.Student;
import com.jaanu.student_portal.postgres.repository.ModuleEnrollmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ModuleEnrollmentService {

    private final ModuleEnrollmentRepository moduleEnrollmentRepository;
    private final ModuleService moduleService;
    private final StudentService studentService;
    private final ActivityLogService activityLogService;

    public ModuleEnrollmentService(
            ModuleEnrollmentRepository moduleEnrollmentRepository,
            ModuleService moduleService,
            StudentService studentService,
            ActivityLogService activityLogService
    ) {
        this.moduleEnrollmentRepository = moduleEnrollmentRepository;
        this.moduleService = moduleService;
        this.studentService = studentService;
        this.activityLogService = activityLogService;
    }

    @Transactional
    public ModuleEnrollment enrollModule(Long moduleId, Long studentId) {

        // 1) Validate entities exist (use your service methods)
        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + studentId));

        Module module = moduleService.getModuleById(moduleId)
                .orElseThrow(() -> new EntityNotFoundException("Module not found: " + moduleId));

        // 2) Prevent duplicate enrollment (optional but recommended)
        if (moduleEnrollmentRepository.existsByStudentIdAndModuleId(studentId, moduleId)) {
            throw new IllegalStateException("Student " + studentId + " already enrolled in module " + moduleId);
        }

        // 3) Create enrollment using relationships
        ModuleEnrollment moduleEnrollment = new ModuleEnrollment();
        moduleEnrollment.setStudent(student);
        moduleEnrollment.setModule(module);

        ModuleEnrollment saved = moduleEnrollmentRepository.save(moduleEnrollment);

        // 4) Log to Mongo
        activityLogService.createLog(
                studentId,
                ActivityType.CREATE_MODULE_ENROLLMENT,
                Map.of(
                        "moduleId", moduleId,
                        "studentId", studentId,
                        "enrollmentId", saved.getId()
                )
        );

        return saved;
    }
}
