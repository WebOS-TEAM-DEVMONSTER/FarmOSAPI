package com.example.farm.demo.domain.post;


import com.example.farm.demo.domain.post.Dto.PostCreateTagDto;
import com.example.farm.demo.domain.post.Dto.PostPatchDto;
import com.example.farm.demo.domain.post.Dto.PostsGetByTagsDto;
import com.example.farm.demo.domain.post.Dto.PostsGetSortedDto;
import com.example.farm.demo.domain.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "게시글 API", description = "Post API")
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
    public ResponseEntity<String> createPost(@RequestBody PostCreateTagDto postCreateDto, Authentication authentication) {

        postService.createPost(postCreateDto, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시글이 생성되었습니다.");
    }


    @GetMapping
    @Operation(summary = "게시글 전체 조회", description = "모든 게시글을 조회합니다.")
    public ResponseEntity<Page<Post>> getAllPosts(@RequestParam PostsGetByTagsDto postsGetByTagsDto, @RequestParam PostsGetSortedDto postsGetSortedDto) {
        Page<Post> postPages = postService.getPostsByPage(postsGetByTagsDto,postsGetSortedDto);
        return ResponseEntity.ok(postPages);
    }

    @GetMapping("/{id}")
    @Operation(summary = "게시글 조회", description = "id를 통해 게시글을 조회합니다.")
    public ResponseEntity<Post> getPostById(@PathVariable("id") String id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/author/{authorId}")
    @Operation(summary = "작성자별 게시글 조회", description = "작성자별 게시글을 조회합니다.")
    public ResponseEntity<Page<Post>> getPostsByAuthorId(@PathVariable("authorId") String authorId, @RequestParam PostsGetSortedDto postsGetSortedDto) {
        Page<Post> posts = postService.getPostsByAuthorId(authorId, postsGetSortedDto);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/title")
    @Operation(summary = "제목별 게시글 조회", description = "제목별 게시글을 조회합니다.")
    public ResponseEntity<Page<Post>> getPostsByTitle(@RequestParam String title, @RequestParam PostsGetSortedDto postsGetSortedDto) {
        Page<Post> pages = postService.getPostsByTitle(title, postsGetSortedDto);

        return ResponseEntity.ok(pages);
    }


    @PatchMapping("/{id}")
    @Operation(summary = "게시글 수정", description = "id를 통해 게시글을 수정합니다.")
    public ResponseEntity<String> updatePost(@PathVariable("id") String id, @RequestBody PostPatchDto postPatchDto) {
        postService.updatePost(id, postPatchDto);
        return ResponseEntity.ok("게시글이 수정되었습니다");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제", description = "id를 통해 게시글을 삭제합니다.")
    public void deletePost(@PathVariable("id") String id) {
        postService.deletePost(id);
    }
}