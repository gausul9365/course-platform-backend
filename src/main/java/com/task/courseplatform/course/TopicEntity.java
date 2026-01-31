package com.task.courseplatform.course;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "topics")
public class TopicEntity {

    @Id
    private String id; // like "kinematics"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SubtopicEntity> getSubtopics() {
        return subtopics;
    }

    public void setSubtopics(List<SubtopicEntity> subtopics) {
        this.subtopics = subtopics;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<SubtopicEntity> subtopics;

    // getters and setters
}
