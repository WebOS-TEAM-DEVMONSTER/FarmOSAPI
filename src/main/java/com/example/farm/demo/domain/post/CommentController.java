package com.example.farm.demo.domain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@Tag(name = "댓글 API", description = "Post API")
@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    @Operation (summary = "게시글 댓글 전체 조회", description = "게시글의 모든 댓글을 조회합니다.")
    public List<Comment> getCommentsByPostId(@PathVariable("postId") String postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/{commentId}")
    @Operation(summary = "게시글 댓글 조회", description = "commentId를 통해 게시글의 댓글을 조회합니다.")
    public Comment getCommentById(@PathVariable("commentId") String commentId) {
        return commentService.getCommentById(commentId);
    }

    @PostMapping
    @Operation(summary = "게시글 댓글 생성", description = "게시글의 댓글을 생성합니다.")
    public Comment createComment(@PathVariable("postId") String postId, @RequestBody Comment comment) {
        return commentService.createComment(postId, comment);
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "게시글 댓글 수정", description = "commentId를 통해 게시글의 댓글을 수정합니다.")
    public Comment updateComment( @PathVariable("commentId") String commentId, @RequestBody Comment commentDetails) {
        return commentService.updateComment(commentId, commentDetails);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "게시글 댓글 삭제", description = "commentId를 통해 게시글의 댓글을 삭제합니다.")
    public void deleteComment(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId) {
        commentService.deleteComment(commentId);
    }
}