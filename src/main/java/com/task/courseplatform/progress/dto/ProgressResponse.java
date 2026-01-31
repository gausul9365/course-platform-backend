package com.task.courseplatform.progress.dto;

import java.util.List;

public class ProgressResponse {

    private Long enrollmentId;
    private String courseId;
    private String courseTitle;
    private int totalSubtopics;
    private int completedSubtopics;
    private double completionPercentage;
    private List<CompletedItemDto> completedItems;

    public ProgressResponse(Long enrollmentId,
                            String courseId,
                            String courseTitle,
                            int totalSubtopics,
                            int completedSubtopics,
                            double completionPercentage,
                            List<CompletedItemDto> completedItems) {
        this.enrollmentId = enrollmentId;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.totalSubtopics = totalSubtopics;
        this.completedSubtopics = completedSubtopics;
        this.completionPercentage = completionPercentage;
        this.completedItems = completedItems;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getTotalSubtopics() {
        return totalSubtopics;
    }

    public int getCompletedSubtopics() {
        return completedSubtopics;
    }

    public double getCompletionPercentage() {
        return completionPercentage;
    }

    public List<CompletedItemDto> getCompletedItems() {
        return completedItems;
    }
}
