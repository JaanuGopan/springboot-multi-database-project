package com.jaanu.student_portal.api.controller;

import com.jaanu.student_portal.postgres.entity.Module;
import com.jaanu.student_portal.postgres.service.ModuleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Module createModule(@RequestBody Module module) {
        return moduleService.createModule(module);
    }

    @PutMapping("/{id}")
    public Module updateModule(@PathVariable Long id, @RequestBody Module req) {
        req.setId(id);
        return moduleService.updateModule(req);
    }

    @GetMapping("/{id}")
    public Module getModule(@PathVariable Long id) {
        return moduleService.getModuleById(id)
                .orElseThrow(() -> new EntityNotFoundException("Module not found: " + id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
    }
}

