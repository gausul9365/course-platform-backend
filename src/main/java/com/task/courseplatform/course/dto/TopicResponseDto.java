package com.task.courseplatform.course.dto;

import java.util.List;

public class TopicResponseDto {

    private String id;
    private String title;
    private List<SubtopicResponseDto> subtopics;

    public TopicResponseDto(String id, String title, List<SubtopicResponseDto> subtopics) {
        this.id = id;
        this.title = title;
        this.subtopics = subtopics;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<SubtopicResponseDto> getSubtopics() {
        return subtopics;
    }
}
