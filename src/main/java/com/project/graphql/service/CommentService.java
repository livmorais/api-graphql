package com.project.graphql.service;

import com.project.graphql.model.Comment;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CommentService {
    public final Map<String, Comment> comments = new HashMap<>();

    public Collection<Comment> createComment(String content, String courseId) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
        var newComment = new Comment(UUID.randomUUID().toString(), content, courseId);
        comments.put(newComment.id(), newComment);
        return comments.values();
    }

    public Collection<Comment> findByCourse(String courseId) {
        return comments.values().stream()
                .filter(comment -> comment.courseId().equals(courseId)).toList();
    }
}
