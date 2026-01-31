package com.task.courseplatform.enrollment;

import com.task.courseplatform.enrollment.dto.EnrollmentResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/{courseId}/enroll")
    public EnrollmentResponse enroll(@PathVariable String courseId,
                                     Authentication authentication) {

        String userEmail = authentication.getName();

        EnrollmentEntity enrollment =
                enrollmentService.enroll(userEmail, courseId);

        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getCourse().getId(),
                enrollment.getCourse().getTitle(),
                enrollment.getEnrolledAt()
        );
    }
}
