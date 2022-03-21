package com.postservice.postservice.service;

import com.postservice.postservice.model.Post;
import com.postservice.postservice.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
@Autowired
    private PostRepo postRepo;

    public Post findById(String postId){
        return this.postRepo.findById(postId).get();
    }



}
