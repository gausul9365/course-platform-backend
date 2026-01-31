package com.task.courseplatform.progress;

import com.task.courseplatform.auth.UserEntity;
import com.task.courseplatform.course.SubtopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubtopicProgressRepository
        extends JpaRepository<SubtopicProgressEntity, Long> {

    boolean existsByUserAndSubtopic(UserEntity user, SubtopicEntity subtopic);

    Optional<SubtopicProgressEntity> findByUserAndSubtopic(
            UserEntity user,
            SubtopicEntity subtopic
    );
}
