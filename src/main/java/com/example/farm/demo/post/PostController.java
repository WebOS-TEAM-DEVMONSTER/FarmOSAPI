package com.example.farm.demo.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@Tag(name = "게시글 API", description = "Post API")
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    @Operation(summary = "게시글 전체 조회", description = "모든 게시글을 조회합니다.")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "게시글 조회", description = "id를 통해 게시글을 조회합니다.")
    public Optional<Post> getPostById(@PathVariable("id") String id) {
        return postService.getPostById(id);
    }

    @GetMapping("/author/{authorId}")
    @Operation(summary = "작성자별 게시글 조회", description = "작성자별 게시글을 조회합니다.")
    public List<Post> getPostsByAuthorId(@PathVariable("authorId") String authorId) {
        return postService.getPostsByAuthorId(authorId);
    }

    @GetMapping("/search")
    @Operation(summary = "제목별 게시글 조회", description = "제목별 게시글을 조회합니다.")
    public List<Post> getPostsByTitle(@RequestParam String title) {
        return postService.getPostsByTitle(title);
    }

    @PostMapping
    @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    @Operation(summary = "게시글 수정", description = "id를 통해 게시글을 수정합니다.")
    public Post updatePost(@PathVariable("id") String id, @RequestBody Post postDetails) {
        return postService.updatePost(id, postDetails);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제", description = "id를 통해 게시글을 삭제합니다.")
    public void deletePost(@PathVariable("id") String id) {
        postService.deletePost(id);
    }
}