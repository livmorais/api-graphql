package com.project.graphql;

import com.project.graphql.model.Comment;
import com.project.graphql.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest {

    private CommentService commentService;

    @BeforeEach
    void setUp() {
        commentService = new CommentService();
    }

    @Test
    void testCreateCommentSuccess() {
        String content = "This is a comment";
        String courseId = "course123";

        Collection<Comment> comments = commentService.createComment(content, courseId);

        assertEquals(1, comments.size());
        Comment comment = comments.iterator().next();
        assertEquals(content, comment.content());
        assertEquals(courseId, comment.courseId());
    }

    @Test
    void testCreateCommentWithNullContent() {
        String content = null;
        String courseId = "course123";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            commentService.createComment(content, courseId);
        });

        assertEquals("Content cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCreateCommentWithEmptyContent() {
        String content = "";
        String courseId = "course123";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            commentService.createComment(content, courseId);
        });

        assertEquals("Content cannot be null or empty", exception.getMessage());
    }

    @Test
    void testFindByCourse() {
        String courseId1 = "course123";
        String courseId2 = "course456";

        commentService.createComment("Comment 1", courseId1);
        commentService.createComment("Comment 2", courseId1);
        commentService.createComment("Comment 3", courseId2);

        Collection<Comment> commentsForCourse1 = commentService.findByCourse(courseId1);
        Collection<Comment> commentsForCourse2 = commentService.findByCourse(courseId2);

        assertEquals(2, commentsForCourse1.size());
        assertEquals(1, commentsForCourse2.size());
    }
}
