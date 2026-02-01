package com.task.courseplatform.course;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.task.courseplatform.course.dto.CourseResponseDto;
import com.task.courseplatform.course.dto.TopicResponseDto;
import com.task.courseplatform.course.dto.SubtopicResponseDto;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true) //  THIS IS THE KEY
    public CourseResponseDto getCourseById(String id) {

        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));


        List<TopicResponseDto> topicDtos = course.getTopics().stream()
                .map(topic -> new TopicResponseDto(
                        topic.getId(),
                        topic.getTitle(),
                        topic.getSubtopics().stream()
                                .map(subtopic -> new SubtopicResponseDto(
                                        subtopic.getId(),
                                        subtopic.getTitle(),
                                        subtopic.getContent()
                                ))
                                .toList()
                ))
                .toList();

        return new CourseResponseDto(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                topicDtos
        );
    }
}
