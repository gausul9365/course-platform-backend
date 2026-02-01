package com.task.courseplatform.course.dto;

import java.util.List;

public class CourseResponseDto {

    private String id;
    private String title;
    private String description;
    private List<TopicResponseDto> topics;

    public CourseResponseDto(String id, String title, String description, List<TopicResponseDto> topics) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.topics = topics;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<TopicResponseDto> getTopics() {
        return topics;
    }
}
