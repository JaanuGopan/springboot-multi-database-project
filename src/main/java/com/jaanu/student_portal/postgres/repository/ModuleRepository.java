package com.jaanu.student_portal.postgres.repository;

import com.jaanu.student_portal.postgres.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {

}
