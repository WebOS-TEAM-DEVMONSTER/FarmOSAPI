package com.example.farm.demo.domain.post;

import com.example.farm.demo.domain.auth.model.User;
import com.example.farm.demo.domain.auth.repository.UserRepository;
import com.example.farm.demo.domain.post.Dto.CommentDto;
import com.example.farm.demo.domain.post.model.Comment;
import com.example.farm.demo.domain.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Comment> getAllCommentsByPostId(String postId) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("id에 해당하는 게시글을 찾지 못했씁니다."));
        return post.sortedComments();
    }

    public void createComment(String postId, CommentDto commentCreateDto, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("id에 해당하는 유저이름이 없습니다."));
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("id에 해당하는 게시글이 없습니다."));
        Comment comment = commentCreateDto.convertDtoToComment(user);
        post.addComment(comment);
        postRepository.save(post);
    }

    public void createTaggedComment(String postId, String commentId, CommentDto commentCreateDto, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("id에 해당하는 유저이름이 없습니다."));
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("id에 해당하는 게시글이 없습니다."));
        Comment comment = commentCreateDto.convertDtoToComment(user);
        Comment parentComment = post.getComments().stream().filter(c->c.getId().equals(commentId)).findFirst()
                        .orElseThrow(()->new RuntimeException("id에 해당하는 댓글이 없습니다."));
        parentComment.addTaggedComment(comment);
        postRepository.save(post);
    }

    public void updateComment(String postId, String commentId, CommentDto commentDto) {

        // post 찾기
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("id에 해당하는 게시글을 찾지 못했씁니다."));
        // comment 찾기
        Comment comment = post.getComments().stream().filter(c->c.getId().equals(commentId)).findFirst()
                .orElseThrow(()->new RuntimeException("id에 해당하는 댓글을 찾지 못했습니다."));
        //comment 수정
        comment.setContent(commentDto.getContent());
        comment.setUpdatedAt(new Date());
        //게시글 저장
        postRepository.save(post);
    }

    public void updateTaggedComment(String postId, String parentCommentId, String commentId, CommentDto commentDto) {

        // post 찾기
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("id에 해당하는 게시글을 찾지 못했씁니다."));
        // comment 찾기
        Comment parentComment = post.getComments().stream().filter(c->c.getId().equals(parentCommentId)).findFirst()
                .orElseThrow(()->new RuntimeException("id에 해당하는 댓글을 찾지 못했습니다."));

        Comment comment = parentComment.getTaggedComments().stream().filter(c->c.getId().equals(commentId)).findFirst()
                .orElseThrow(()->new RuntimeException("id에 해당하는 대댓글을 찾을 수 없습니다."));

        //comment 수정
        comment.setContent(commentDto.getContent());
        comment.setUpdatedAt(new Date());
        //게시글 저장
        postRepository.save(post);
    }

    public void deleteComment(String postId, String commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("id에 해당하는 게시글을 찾지 못했씁니다."));
        Comment comment = post.getComments().stream().filter(c->c.getId().equals(commentId)).findFirst()
                        .orElseThrow(()->new RuntimeException("id에 해당하는 댓글을 찾지 못했습니다."));

        post.getComments().remove(comment);
        postRepository.save(post);
    }

    public void deleteTaggedComment(String postId, String parentCommentId, String commentId) {
        // 1. postId로 게시글 찾기
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("id에 해당하는 게시글을 찾지 못했습니다."));

        // 부모 댓글의 taggedComments에서 해당 댓글을 찾는 경우
        Comment parentComment = post.getComments().stream()
                .filter(c -> c.getId().equals(parentCommentId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("부모 댓글을 찾지 못했습니다."));

        // 부모 댓글의 taggedComments에서 commentId로 댓글 찾기
        Comment comment = parentComment.getTaggedComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("id에 해당하는 댓글을 찾지 못했습니다."));

        // taggedComment 삭제
        parentComment.getTaggedComments().remove(comment);
        // 4. 변경된 post 저장
        postRepository.save(post);
    }
}
