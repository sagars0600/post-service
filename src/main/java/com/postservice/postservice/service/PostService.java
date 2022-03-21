package com.postservice.postservice.service;

import com.postservice.postservice.model.Post;
import com.postservice.postservice.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {
@Autowired
    private PostRepo postRepo;

    public Post updatePost(Post post, String postId){
        post.setUpdatedAt(LocalDateTime.now());
        return this.postRepo.save(post);
    }


}
