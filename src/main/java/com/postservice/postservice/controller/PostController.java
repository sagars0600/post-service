package com.postservice.postservice.controller;

import com.postservice.postservice.model.Post;
import com.postservice.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<Post> savePost(@RequestBody @Valid Post post){
        return  new ResponseEntity<>(postService.savePost(post), HttpStatus.ACCEPTED);
    }
}
