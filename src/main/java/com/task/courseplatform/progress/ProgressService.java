package com.task.courseplatform.progress;

import com.task.courseplatform.auth.UserEntity;
import com.task.courseplatform.auth.UserRepository;
import com.task.courseplatform.course.SubtopicEntity;
import com.task.courseplatform.course.SubtopicRepository;
import com.task.courseplatform.enrollment.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;



import com.task.courseplatform.enrollment.EnrollmentEntity;
import com.task.courseplatform.enrollment.EnrollmentRepository;
import com.task.courseplatform.progress.dto.CompletedItemDto;
import com.task.courseplatform.progress.dto.ProgressResponse;

import java.util.List;
import java.util.stream.Collectors;




@Service
public class ProgressService {

    private final SubtopicProgressRepository progressRepository;
    private final SubtopicRepository subtopicRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;

    public ProgressService(SubtopicProgressRepository progressRepository,
                           SubtopicRepository subtopicRepository,
                           EnrollmentRepository enrollmentRepository,
                           UserRepository userRepository) {
        this.progressRepository = progressRepository;
        this.subtopicRepository = subtopicRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
    }

    public SubtopicProgressEntity markCompleted(String userEmail, String subtopicId) {

        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        SubtopicEntity subtopic = subtopicRepository.findById(subtopicId)
                .orElseThrow(() -> new IllegalArgumentException("Subtopic not found"));

        String courseId = subtopic.getTopic().getCourse().getId();

        boolean enrolled = enrollmentRepository
                .existsByUserAndCourse(user, subtopic.getTopic().getCourse());

        if (!enrolled) {
            throw new IllegalStateException("User not enrolled in this course");
        }

        return progressRepository.findByUserAndSubtopic(user, subtopic)
                .orElseGet(() -> {
                    SubtopicProgressEntity progress = new SubtopicProgressEntity();
                    progress.setUser(user);
                    progress.setSubtopic(subtopic);
                    progress.setCompleted(true);
                    progress.setCompletedAt(Instant.now());
                    return progressRepository.save(progress);
                });
    }


    public ProgressResponse getProgress(Long enrollmentId, String userEmail) {

        EnrollmentEntity enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));

        if (!enrollment.getUser().getEmail().equals(userEmail)) {
            throw new IllegalStateException("Forbidden");
        }

        int totalSubtopics = enrollment.getCourse()
                .getTopics()
                .stream()
                .mapToInt(t -> t.getSubtopics().size())
                .sum();

        List<CompletedItemDto> completedItems =
                progressRepository.findAll().stream()
                        .filter(p ->
                                p.getUser().getId().equals(enrollment.getUser().getId()) &&
                                        p.getSubtopic().getTopic().getCourse().getId()
                                                .equals(enrollment.getCourse().getId())
                        )
                        .map(p -> new CompletedItemDto(
                                p.getSubtopic().getId(),
                                p.getSubtopic().getTitle(),
                                p.getCompletedAt()
                        ))
                        .collect(Collectors.toList());

        int completedCount = completedItems.size();

        double percentage = totalSubtopics == 0
                ? 0
                : (completedCount * 100.0) / totalSubtopics;

        return new ProgressResponse(
                enrollment.getId(),
                enrollment.getCourse().getId(),
                enrollment.getCourse().getTitle(),
                totalSubtopics,
                completedCount,
                percentage,
                completedItems
        );
    }

}
