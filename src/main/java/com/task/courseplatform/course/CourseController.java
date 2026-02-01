package com.task.courseplatform.course;

import com.task.courseplatform.course.dto.CourseResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    public List<CourseEntity> getAllCourses() {
        return courseService.getAllCourses();
    }


    @GetMapping("/{id}")
    public CourseResponseDto getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id);
    }

}
