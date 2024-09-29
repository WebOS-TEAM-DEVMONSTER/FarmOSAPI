package com.example.farm.demo.domain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByPostId(String postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment getCommentById(String commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if (commentOptional.isPresent()) {
            return commentOptional.get();
        } else {
            throw new RuntimeException("Comment not found with id " + commentId);
        }
    }


    public Comment createComment(String postId, Comment comment) {
        comment.setPostId(postId);
        return commentRepository.save(comment);
    }

    public Comment updateComment(String commentId, Comment commentDetails) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setContent(commentDetails.getContent());
            comment.setUpdatedAt(new Date());
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("Post not found with id " + commentId);
        }
    }

    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }
}
