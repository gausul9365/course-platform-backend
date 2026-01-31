package com.task.courseplatform.enrollment;

import com.task.courseplatform.auth.UserEntity;
import com.task.courseplatform.course.CourseEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(
        name = "enrollments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "course_id"})
        }
)
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private CourseEntity course;

    private Instant enrolledAt;

    // getters and setters
}
