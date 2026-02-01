package com.task.courseplatform.enrollment;

import com.task.courseplatform.course.CourseEntity;
import com.task.courseplatform.course.CourseRepository;
import com.task.courseplatform.auth.UserEntity;
import com.task.courseplatform.auth.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             UserRepository userRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public EnrollmentEntity enroll(String userEmail, String courseId) {

        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found"));


        if (enrollmentRepository.existsByUserAndCourse(user, course)) {
            throw new IllegalStateException("Already enrolled");
        }

        EnrollmentEntity enrollment = new EnrollmentEntity();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(Instant.now());

        return enrollmentRepository.save(enrollment);
    }

}
