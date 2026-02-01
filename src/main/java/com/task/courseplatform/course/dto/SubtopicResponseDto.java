package com.task.courseplatform.course.dto;

public class SubtopicResponseDto {

    private String id;
    private String title;
    private String content;

    public SubtopicResponseDto(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
