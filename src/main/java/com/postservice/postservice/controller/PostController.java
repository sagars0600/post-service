package com.postservice.postservice.controller;

import com.postservice.postservice.model.Post;
import com.postservice.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PutMapping("/posts/{postId}")
    public ResponseEntity<Post> updatePost(@RequestBody @Valid Post post, @PathVariable("postId") String postId){
        return  new ResponseEntity<>(postService.updatePost(post,postId), HttpStatus.ACCEPTED);
    }
}
