package com.example.farm.demo.domain.post;

import com.example.farm.demo.domain.post.Dto.CommentDto;
import com.example.farm.demo.domain.post.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@Tag(name = "댓글 API", description = "Post API")
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{postId}")
    @Operation (summary = "게시글 댓글 전체 조회", description = "게시글의 모든 댓글을 조회합니다.")
    public List<Comment> getCommentsByPostId(@PathVariable("postId") String postId) {
        return commentService.getAllCommentsByPostId(postId);
    }

    @PostMapping("/post/{postId}")
    @Operation(summary = "게시글 댓글 생성", description = "게시글의 댓글을 생성합니다.")
    public ResponseEntity<String> createComment(@PathVariable("postId") String postId, @RequestBody CommentDto commentCreateDto, Authentication authentication) {
        commentService.createComment(postId, commentCreateDto, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시글 댓글이 생성되었습니다.");
    }

    @PostMapping("/{commentId}/tagged/")
    @Operation(summary = "대댓글 생성", description = "게시글의 댓글을 생성합니다.")
    public ResponseEntity<String> createTaggedComment( @PathVariable("commentId") String commentId, @RequestBody CommentDto commentCreateDto, Authentication authentication) {
        commentService.createTaggedComment(commentId, commentCreateDto, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body("대댓글이 생성되었습니다.");
    }



    @PatchMapping("/{commentId}")
    @Operation(summary = "게시글 댓글 수정", description = "commentId를 통해 게시글의 댓글을 수정합니다.")
    public ResponseEntity<String> updateComment(@PathVariable("commentId") String commentId, @RequestBody CommentDto commentDto) {
        commentService.updateComment(commentId, commentDto);
        return ResponseEntity.ok("게시들 댓글이 성공적으로 수정되었습니다.");
    }

    @PatchMapping("/{parentCommentId}/tagged/{commentId}")
    @Operation(summary = "대댓글 수정", description = "commentId를 통해 대댓글을 수정합니다.")
    public ResponseEntity<String> updateTaggedComment(@PathVariable("parentCommentId") String parentCommentId, @PathVariable("commentId") String commentId, @RequestBody CommentDto commentDto) {
        commentService.updateTaggedComment(parentCommentId, commentId, commentDto);
        return ResponseEntity.ok("게시들 댓글이 성공적으로 수정되었습니다.");
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "게시글 댓글 삭제", description = "commentId를 통해 게시글의 댓글을 삭제합니다.")
    public ResponseEntity<String> deleteComment( @PathVariable("commentId") String commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
    }

    @DeleteMapping("/{parentCommentId}/tagged/{commentId}")
    @Operation(summary = "대댓글 삭제", description = "commentId를 통해 대댓글울 석재합니다.")
    public ResponseEntity<String> deleteTaggedComment(@PathVariable("parentCommentId") String parentCommentId, @PathVariable("commentId") String commentId) {
        commentService.deleteTaggedComment(parentCommentId, commentId);
        return ResponseEntity.ok("게시글 댓글이 성공적으로 삭제되었습니다.");
    }
}