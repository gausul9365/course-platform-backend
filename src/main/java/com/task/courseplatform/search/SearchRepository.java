package com.task.courseplatform.search;

import com.task.courseplatform.course.CourseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchRepository extends Repository<CourseEntity, String> {

    @Query(value = """
        select distinct c.id
        from courses c
        left join topics t on t.course_id = c.id
        left join subtopics s on s.topic_id = t.id
        where
            lower(c.title) like lower(concat('%', :q, '%'))
            or lower(c.description) like lower(concat('%', :q, '%'))
            or lower(t.title) like lower(concat('%', :q, '%'))
            or lower(s.title) like lower(concat('%', :q, '%'))
            or lower(s.content) like lower(concat('%', :q, '%'))
        """, nativeQuery = true)
    List<String> searchCourseIds(@Param("q") String query);
}
