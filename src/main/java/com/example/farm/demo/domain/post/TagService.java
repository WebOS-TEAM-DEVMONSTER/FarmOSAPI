package com.example.farm.demo.domain.post;

import java.util.List;

public class TagService {

    TagRepository tagRepository;

    void addPostInTag(String postId, List<String> tagNames){
        for(String tagName : tagNames){
            tagRepository.addFarmIdToTag(postId,tagName);
        }
    }

    void removePostInTag(String postId, String tagName){
        tagRepository.removeFarmIdFromTag(postId,tagName);
    }

    void removePostInAllTag(String postId){
        tagRepository.removePostIdFromAllTags(postId);
    }


}
