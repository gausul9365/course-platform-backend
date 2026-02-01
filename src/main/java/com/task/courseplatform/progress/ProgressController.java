package com.task.courseplatform.progress;

import com.task.courseplatform.progress.dto.ProgressResponse;
import com.task.courseplatform.progress.dto.SubtopicCompletionResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subtopics")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/{subtopicId}/complete")
    public SubtopicCompletionResponse completeSubtopic(
            @PathVariable String subtopicId,
            Authentication authentication
    ) {

        SubtopicProgressEntity progress =
                progressService.markCompleted(authentication.getName(), subtopicId);

        return new SubtopicCompletionResponse(
                progress.getSubtopic().getId(),
                progress.isCompleted(),
                progress.getCompletedAt()
        );
    }

    @GetMapping("/enrollments/{enrollmentId}/progress")
    public ProgressResponse viewProgress(
            @PathVariable Long enrollmentId,
            Authentication authentication
    ) {
        return progressService.getProgress(
                enrollmentId,
                authentication.getName()
        );
    }
}
