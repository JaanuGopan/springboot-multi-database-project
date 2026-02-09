package com.jaanu.student_portal.api.controller;

import com.jaanu.student_portal.postgres.entity.ModuleEnrollment;
import com.jaanu.student_portal.postgres.service.ModuleEnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
public class ModuleEnrollmentController {

    private final ModuleEnrollmentService moduleEnrollmentService;

    public ModuleEnrollmentController(ModuleEnrollmentService moduleEnrollmentService) {
        this.moduleEnrollmentService = moduleEnrollmentService;
    }

    @PostMapping("/students/{studentId}/modules/{moduleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ModuleEnrollment enrollModule(@PathVariable Long studentId,
                                         @PathVariable Long moduleId) {
        return moduleEnrollmentService.enrollModule(moduleId, studentId);
    }
}

