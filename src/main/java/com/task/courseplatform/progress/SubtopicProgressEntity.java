package com.task.courseplatform.progress;

import com.task.courseplatform.auth.UserEntity;
import com.task.courseplatform.course.SubtopicEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(
        name = "subtopic_progress",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "subtopic_id"})
        }
)
public class SubtopicProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private SubtopicEntity subtopic;

    private boolean completed;

    private Instant completedAt;

    // getters and setters
}
