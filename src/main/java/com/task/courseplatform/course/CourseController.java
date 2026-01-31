package com.task.courseplatform.course;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // GET /api/courses
    @GetMapping
    public List<CourseEntity> getAllCourses() {
        return courseService.getAllCourses();
    }

    // GET /api/courses/{id}
    @GetMapping("/{id}")
    public CourseEntity getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id);
    }
}
