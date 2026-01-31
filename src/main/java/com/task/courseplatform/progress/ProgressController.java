package com.task.courseplatform.progress;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.task.courseplatform.progress.dto.ProgressResponse;



@RestController
@RequestMapping("/api/subtopics")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/{subtopicId}/complete")
    public SubtopicProgressEntity completeSubtopic(@PathVariable String subtopicId,
                                                   Authentication authentication) {

        String userEmail = authentication.getName();
        return progressService.markCompleted(userEmail, subtopicId);
    }


    @GetMapping("/enrollments/{enrollmentId}/progress")
    public ProgressResponse viewProgress(@PathVariable Long enrollmentId,
                                         Authentication authentication) {

        return progressService.getProgress(
                enrollmentId,
                authentication.getName()
        );
    }

}
