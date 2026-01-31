package com.task.courseplatform.seed;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.courseplatform.course.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class SeedDataLoader implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final TopicRepository topicRepository;
    private final SubtopicRepository subtopicRepository;

    public SeedDataLoader(
            CourseRepository courseRepository,
            TopicRepository topicRepository,
            SubtopicRepository subtopicRepository
    ) {
        this.courseRepository = courseRepository;
        this.topicRepository = topicRepository;
        this.subtopicRepository = subtopicRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // load data only if DB is empty
        if (courseRepository.count() > 0) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/data/courses.json");

        JsonNode root = mapper.readTree(is);

        for (JsonNode courseNode : root.get("courses")) {

            CourseEntity course = new CourseEntity();
            course.setId(courseNode.get("id").asText());
            course.setTitle(courseNode.get("title").asText());
            course.setDescription(courseNode.get("description").asText());

            courseRepository.save(course);

            for (JsonNode topicNode : courseNode.get("topics")) {

                TopicEntity topic = new TopicEntity();
                topic.setId(topicNode.get("id").asText());
                topic.setTitle(topicNode.get("title").asText());
                topic.setCourse(course);

                topicRepository.save(topic);

                for (JsonNode subtopicNode : topicNode.get("subtopics")) {

                    SubtopicEntity subtopic = new SubtopicEntity();
                    subtopic.setId(subtopicNode.get("id").asText());
                    subtopic.setTitle(subtopicNode.get("title").asText());
                    subtopic.setContent(subtopicNode.get("content").asText());
                    subtopic.setTopic(topic);

                    subtopicRepository.save(subtopic);
                }
            }
        }
    }
}
