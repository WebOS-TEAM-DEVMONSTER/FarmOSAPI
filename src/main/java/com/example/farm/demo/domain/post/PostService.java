package com.example.farm.demo.domain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(String id) {
        return postRepository.findById(id);
    }

    public List<Post> getPostsByAuthorId(String authorId) {
        return postRepository.findByAuthorId(authorId);
    }

    public List<Post> getPostsByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }

    public Post createPost(Post post) {

        return postRepository.save(post);
    }

    public Post updatePost(String id, Post postDetails) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            return postRepository.save(post);
        } else {
            throw new RuntimeException("Post not found with id " + id);
        }
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }
}
