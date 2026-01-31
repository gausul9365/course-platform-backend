package com.task.courseplatform.enrollment;

import com.task.courseplatform.auth.UserEntity;
import com.task.courseplatform.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {

    boolean existsByUserAndCourse(UserEntity user, CourseEntity course);
}
