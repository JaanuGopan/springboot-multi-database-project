package com.jaanu.student_portal.postgres.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moduleName;

    private String moduleCode;

    @OneToMany(mappedBy = "module",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ModuleEnrollment> enrollments = new ArrayList<>();

    public Module(Long id, String moduleName, String moduleCode, List<ModuleEnrollment> enrollments) {
        this.id = id;
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.enrollments = enrollments;
    }

    public Module() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public List<ModuleEnrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<ModuleEnrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
