package com.project.graphql;

import com.project.graphql.model.Course;
import com.project.graphql.model.Topic;
import com.project.graphql.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    private CourseService courseService;

    @BeforeEach
    void setUp() {
        courseService = new CourseService();
    }

    @Test
    void testCreateCourse() {
        Topic topic = Topic.JAVA;
        Collection<Course> courses = courseService.createCourse("Java Basics", "Introduction to Java", topic);
        assertEquals(1, courses.size());
        Course course = courses.iterator().next();
        assertEquals("Java Basics", course.name());
        assertEquals("Introduction to Java", course.description());
        assertEquals(topic, course.topic());
    }

    @Test
    void testCreateCourseWithNullName() {
        Topic topic = Topic.JAVA;
        assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse(null, "Introduction to Java", topic);
        });
    }

    @Test
    void testCreateCourseWithEmptyName() {
        Topic topic = Topic.JAVA;
        assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse("", "Introduction to Java", topic);
        });
    }

    @Test
    void testCreateCourseWithNullTopic() {
        assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse("Java Basics", "Introduction to Java", null);
        });
    }

    @Test
    void testAllCourses() {
        Topic topic = Topic.JAVA;
        courseService.createCourse("Java Basics", "Introduction to Java", topic);
        Collection<Course> courses = courseService.allCourses();
        assertEquals(1, courses.size());
    }

    @Test
    void testCourseById() {
        Topic topic = Topic.JAVA;
        Collection<Course> courses = courseService.createCourse("Java Basics", "Introduction to Java", topic);
        Course course = courses.iterator().next();
        Course foundCourse = courseService.courseById(course.id());
        assertNotNull(foundCourse);
        assertEquals(course.id(), foundCourse.id());
    }

    @Test
    void testCourseByIdNotFound() {
        Course course = courseService.courseById(UUID.randomUUID().toString());
        assertNull(course);
    }

    @Test
    void testCoursesByTopic() {
        Topic topicJava = Topic.JAVA;
        Topic topicPython = Topic.PYTHON;
        courseService.createCourse("Java Basics", "Introduction to Java", topicJava);
        courseService.createCourse("Python Basics", "Introduction to Python", topicPython);
        Collection<Course> javaCourses = courseService.coursesByTopic(topicJava);
        assertEquals(1, javaCourses.size());
        assertEquals(topicJava, javaCourses.iterator().next().topic());
    }

    @Test
    void testClearCourses() {
        Topic topic = Topic.JAVA;
        courseService.createCourse("Java Basics", "Introduction to Java", topic);
        courseService.clearCourses();
        Collection<Course> courses = courseService.allCourses();
        assertTrue(courses.isEmpty());
    }
}
