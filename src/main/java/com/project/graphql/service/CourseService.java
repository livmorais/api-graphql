package com.project.graphql.service;

import com.project.graphql.model.Course;
import com.project.graphql.model.Topic;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CourseService {
    public final Map<String, Course> courses = new HashMap<>();

    public Collection<Course> createCourse(String name, String description, Topic topic) {
        if (name == null || name.isEmpty() || topic == null) {
            throw new IllegalArgumentException("Name and topic cannot be null or empty");
        }
        var newCourse = new Course(UUID.randomUUID().toString(), name, description, topic);
        courses.put(newCourse.id(), newCourse);
        return courses.values();
    }

    public Collection<Course> allCourses() {
        return courses.values();
    }

    public Course courseById(String id) {
        return courses.get(id);
    }

    public Collection<Course> coursesByTopic(Topic topic) {
        return courses.values().stream()
                .filter(course -> course.topic().equals(topic)).toList();
    }

    public void clearCourses() {
        courses.clear();
    }
}
