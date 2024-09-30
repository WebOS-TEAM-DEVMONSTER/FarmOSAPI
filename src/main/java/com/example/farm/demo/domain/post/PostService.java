package com.example.farm.demo.domain.post;

import com.example.farm.demo.domain.auth.model.User;
import com.example.farm.demo.domain.auth.repository.UserRepository;
import com.example.farm.demo.domain.farm.Farm;
import com.example.farm.demo.domain.farm.FarmRepository;
import com.example.farm.demo.domain.post.Dto.PostPatchDto;
import com.example.farm.demo.domain.post.Dto.PostsGetByTagsDto;
import com.example.farm.demo.domain.post.Dto.PostsGetSortedDto;
import com.example.farm.demo.domain.post.Dto.PostCreateTagDto;
import com.example.farm.demo.domain.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FarmRepository farmRepository;

    public void createPost(PostCreateTagDto postCreateDto, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("토큰 정보를 통해 유저 정보를 추출할 수 없습니다."));
        Farm farm = farmRepository.findById(postCreateDto.getFarmId()).orElseThrow(()-> new RuntimeException("농장 정보를 가져올 수 없습니다."));
        Post post = postCreateDto.getPostFromDto(user, farm);
        postRepository.save(post);
    }

//    public List<Post> getAllPosts() {
//        return postRepository.findAll();
//    }

    public Page<Post> getPostsByPage(PostsGetByTagsDto postsGetByTagsDto, PostsGetSortedDto postCreateSortDto) {

        Pageable pageable = postCreateSortDto.convertSortDtoToPageable();
        if(postsGetByTagsDto.getTags().isEmpty()){
            return postRepository.findAll(pageable);
        } else {
            List<String> tags = postsGetByTagsDto.getTags();
            return postRepository.findByTagsIn(tags, pageable);
        }
    }

    public Post getPostById(String id) {
        return postRepository.findById(id).orElseThrow(()-> new RuntimeException("해당 ID의 게시글이 없습니다."));
    }

    public Page<Post> getPostsByAuthorId(String authorId, PostsGetSortedDto postsGetSortedDto) {
        Pageable pageable = postsGetSortedDto.convertSortDtoToPageable();
        return postRepository.findByAuthor_Id(authorId, pageable);
    }

    public Page<Post> getPostsByTitle(String title, PostsGetSortedDto postsGetSortedDto) {
        Pageable pageable = postsGetSortedDto.convertSortDtoToPageable();
        return postRepository.findByTitleContaining(title, pageable);
    }




    public void updatePost(String id, PostPatchDto postPatchDto) {
        Post post = postRepository.findById(id).orElseThrow(()->new RuntimeException("id에 해당하는 게시글이 없습니다."));
        postPatchDto.patchPost(post);
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }
}
