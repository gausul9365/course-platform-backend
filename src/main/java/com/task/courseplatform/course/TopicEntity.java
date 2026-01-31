package com.task.courseplatform.course;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "topics")
public class TopicEntity {

    @Id
    private String id; // like "kinematics"

    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<SubtopicEntity> subtopics;

    // getters and setters
}
