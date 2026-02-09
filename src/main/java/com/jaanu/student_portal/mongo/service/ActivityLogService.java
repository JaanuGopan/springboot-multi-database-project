package com.jaanu.student_portal.mongo.service;

import com.jaanu.student_portal.mongo.document.ActivityLog;
import com.jaanu.student_portal.mongo.document.ActivityType;
import com.jaanu.student_portal.mongo.repository.ActivityLogRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;


    public ActivityLogService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    public void createLog(Long userId, ActivityType activityType, Map<String, Object> metadata) {
        ActivityLog activityLog = new ActivityLog();
        activityLog.setStudentId(userId);
        activityLog.setAction(activityType);
        activityLog.setMetadata(metadata);
        activityLog.setTimestamp(Instant.now());

        activityLogRepository.save(activityLog);
    }
}
