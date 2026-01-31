package com.task.courseplatform.progress;

import com.task.courseplatform.auth.UserEntity;
import com.task.courseplatform.course.SubtopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtopicProgressRepository extends JpaRepository<SubtopicProgressEntity, Long> {

    boolean existsByUserAndSubtopic(UserEntity user, SubtopicEntity subtopic);
}
