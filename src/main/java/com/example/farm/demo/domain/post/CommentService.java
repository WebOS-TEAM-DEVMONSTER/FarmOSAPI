package com.example.farm.demo.domain.post;

import com.example.farm.demo.domain.auth.model.User;
import com.example.farm.demo.domain.auth.repository.UserRepository;
import com.example.farm.demo.domain.post.Dto.CommentDto;
import com.example.farm.demo.domain.post.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CommentService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    // 특정 게시물에 달린 모든 댓글 가져오기
    public List<Comment> getAllCommentsByPostId(String postId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");
        return commentRepository.findByPostId(postId, sort);
    }

    // 댓글 생성하기
    public void createComment(String postId, CommentDto commentCreateDto, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저 이름을 찾을 수 없습니다."));
        Comment comment = commentCreateDto.convertDtoToComment(user, postId, null);
        commentRepository.save(comment);
    }

    // 대댓글 생성하기
    public void createTaggedComment(String commentId, CommentDto commentCreateDto, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저 이름을 찾을 수 없습니다."));
        Comment comment = commentCreateDto.convertDtoToComment(user, null, commentId);
        // 대댓글에 수동으로 id 생성
        comment.setId(UUID.randomUUID().toString());

        Comment parentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));
        parentComment.addTaggedComment(comment);
        commentRepository.save(parentComment);
    }

    // 댓글 수정하기
    public void updateComment(String commentId, CommentDto commentDto) {

        // comment 찾기
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));
        //comment 수정
        comment.setContent(commentDto.getContent());
        comment.setUpdatedAt(new Date());
        //댓글 저장
        commentRepository.save(comment);
    }

    // 대댓글 수정하기
    public void updateTaggedComment(String parentCommentId, String commentId, CommentDto commentDto) {

        // 부모 댓글 찾기
        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));

        // 대댓글 찾기
        Comment comment = parentComment.getTaggedComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "대댓글을 찾을 수 없습니다."));

        //대댓글 수정
        comment.setContent(commentDto.getContent());
        comment.setUpdatedAt(new Date());
        //부모 댓글 저장
        commentRepository.save(parentComment);
    }

    // 댓글 삭제하기
    public void deleteComment(String commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));
        commentRepository.delete(comment);
    }

    // 대댓글 삭제하기
    public void deleteTaggedComment(String parentCommentId, String commentId) {

        // 부모 댓글 찾기
        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));

        // 부모 댓글의 taggedComments에서 commentId로 대댓글 찾기
        Comment comment = parentComment.getTaggedComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "대댓글을 찾을 수 없습니다."));

        // taggedComment 삭제
        parentComment.getTaggedComments().remove(comment);
        // 변경된 부모 댓글 저장
        commentRepository.save(parentComment);
    }
}
