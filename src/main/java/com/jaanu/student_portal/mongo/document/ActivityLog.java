package com.jaanu.student_portal.mongo.document;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Map;

@Document(collection = "activity_logs")
public class ActivityLog {
    @Id
    @Field("id")
    private String activityId;

    @Field("user_id")
    private Long userId;

    @Field("action")
    private ActivityType action;

    @Field("metadata")
    private Map<String, Object> metadata;

    @Field("timestamp")
    private Instant timestamp;

    public ActivityLog(String activityId, Long userId, ActivityType action, Map<String, Object> metadata, Instant timestamp) {
        this.activityId = activityId;
        this.userId = userId;
        this.action = action;
        this.metadata = metadata;
        this.timestamp = timestamp;
    }

    public ActivityLog() {
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Long getStudentId() {
        return userId;
    }

    public void setStudentId(Long userId) {
        this.userId = userId;
    }

    public ActivityType getAction() {
        return action;
    }

    public void setAction(ActivityType action) {
        this.action = action;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
