package com.project.graphql.controller;

import java.util.Collection;

import com.project.graphql.*;
import com.project.graphql.model.Comment;
import com.project.graphql.model.Course;
import com.project.graphql.model.Topic;
import com.project.graphql.service.CommentService;
import com.project.graphql.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    CommentService commentService;

    @QueryMapping
    public Collection<Course> allCourses() {
        return courseService.allCourses();
    }

    @QueryMapping
    public Course courseById(@Argument String id) {
        return courseService.courseById(id);
    }

    @QueryMapping
    public Collection<Course> coursesByTopic(@Argument Topic topic) {
        return courseService.coursesByTopic(topic);
    }

    @MutationMapping
    public Collection<Course> createCourse(@Argument String name, @Argument String description, @Argument Topic topic) {
        return courseService.createCourse(name, description, topic);
    }

    @MutationMapping
    public Collection<Comment> createComment(@Argument String content, @Argument String courseId) {
        return commentService.createComment(content, courseId);
    }

    @SchemaMapping
    public Collection<Comment> comments(Course course) {
        return commentService.findByCourse(course.id());
    }
}
