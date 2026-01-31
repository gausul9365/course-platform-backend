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

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    // getters and setters
}
