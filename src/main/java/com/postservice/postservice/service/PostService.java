package com.postservice.postservice.service;

import com.postservice.postservice.model.Post;
import com.postservice.postservice.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;



import java.time.LocalDateTime;

import java.util.List;



@Service
public class PostService {
@Autowired
    private PostRepo postRepo;


    public Post savePost(Post post){
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        return postRepo.save(post);
    }
    public Post updatePost(Post post, String postId){
        post.setUpdatedAt(LocalDateTime.now());
        return this.postRepo.save(post);
    }

    public String deleteById(String postId){
        this.postRepo.deleteById(postId);
        return "Post id "+postId+ " Deleted Successfully";
    }


    public List<Post> showAll(){
        return postRepo.findAll();

    }



}
