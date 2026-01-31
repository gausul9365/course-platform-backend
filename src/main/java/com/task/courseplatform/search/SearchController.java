package com.task.courseplatform.search;

import com.task.courseplatform.course.CourseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    // GET /api/search?q=velocity
    @GetMapping
    public List<CourseEntity> search(@RequestParam String q) {
        return searchService.searchCourses(q);
    }
}
