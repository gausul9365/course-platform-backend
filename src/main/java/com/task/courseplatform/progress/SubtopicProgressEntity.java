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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public SubtopicEntity getSubtopic() {
        return subtopic;
    }

    public void setSubtopic(SubtopicEntity subtopic) {
        this.subtopic = subtopic;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @ManyToOne
    private SubtopicEntity subtopic;

    private boolean completed;

    private Instant completedAt;


}
