package com.task.courseplatform.search;

import com.task.courseplatform.course.CourseEntity;
import com.task.courseplatform.course.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final SearchRepository searchRepository;
    private final CourseRepository courseRepository;

    public SearchService(SearchRepository searchRepository,
                         CourseRepository courseRepository) {
        this.searchRepository = searchRepository;
        this.courseRepository = courseRepository;
    }

    public List<CourseEntity> searchCourses(String query) {

        List<String> courseIds = searchRepository.searchCourseIds(query);
        List<CourseEntity> results = new ArrayList<>();

        for (String courseId : courseIds) {
            courseRepository.findById(courseId)
                    .ifPresent(results::add);
        }

        return results;
    }
}
