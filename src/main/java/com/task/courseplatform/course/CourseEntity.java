package com.task.courseplatform.course;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    private String id; // like "physics-101"

    private String title;

    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<TopicEntity> topics;

    // getters and setters
}
