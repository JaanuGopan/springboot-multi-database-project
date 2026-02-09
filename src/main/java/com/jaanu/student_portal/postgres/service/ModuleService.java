package com.jaanu.student_portal.postgres.service;

import com.jaanu.student_portal.mongo.document.ActivityType;
import com.jaanu.student_portal.mongo.service.ActivityLogService;
import com.jaanu.student_portal.postgres.entity.Module;
import com.jaanu.student_portal.postgres.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final ActivityLogService activityLogService;


    public ModuleService(ModuleRepository moduleRepository, ActivityLogService activityLogService) {
        this.moduleRepository = moduleRepository;
        this.activityLogService = activityLogService;
    }

    public Module createModule(Module module) {
        Module savedModule = moduleRepository.save(module);

        activityLogService.createLog(
                savedModule.getId(),
                ActivityType.CREATE_MODULE,
                Map.of(
                        "moduleName", savedModule.getModuleName(),
                        "moduleCode", savedModule.getModuleCode()
                )
        );

        return savedModule;
    }

    public Module updateModule(Module module) {
        Module savedModule = moduleRepository.findById(module.getId()).orElseThrow(
                () -> new IllegalArgumentException("Module not found by this module id " + module.getId())
        );

        savedModule.setModuleCode(module.getModuleCode());
        savedModule.setModuleName(module.getModuleName());

        activityLogService.createLog(
                savedModule.getId(),
                ActivityType.UPDATE_MODULE,
                Map.of(
                        "moduleName", savedModule.getModuleName(),
                        "moduleCode", savedModule.getModuleCode()
                )
        );

        return moduleRepository.save(savedModule);
    }

    public Optional<Module> getModuleById(Long moduleId) {
        return moduleRepository.findById(moduleId);
    }

    public void deleteModule(Long moduleId){
        Module module = moduleRepository.findById(moduleId).orElseThrow(
                () -> new IllegalArgumentException("Module not found by this module id " + moduleId)
        );

        moduleRepository.delete(module);

        activityLogService.createLog(
                module.getId(),
                ActivityType.DELETE_MODULE,
                Map.of(
                        "moduleName", module.getModuleName(),
                        "moduleCode", module.getModuleCode()
                )
        );
    }

}
