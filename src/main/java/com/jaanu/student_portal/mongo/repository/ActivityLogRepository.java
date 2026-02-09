package com.jaanu.student_portal.mongo.repository;

import com.jaanu.student_portal.mongo.document.ActivityLog;
import com.jaanu.student_portal.mongo.document.ActivityType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ActivityLogRepository extends MongoRepository<ActivityLog, String> {
    List<ActivityLog> findAllByStudentId(Long studentId);

    List<ActivityLog> findAllByAction(ActivityType action);
}