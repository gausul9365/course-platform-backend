package com.task.courseplatform.course;

import jakarta.persistence.*;

@Entity
@Table(name = "subtopics")
public class SubtopicEntity {

    @Id
    private String id; // like "velocity"

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content; // markdown text

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    // getters and setters
}
