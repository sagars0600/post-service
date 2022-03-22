package com.postservice.postservice.controller;

import com.postservice.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deleteById(@PathVariable("postId") String postId){
        return  new ResponseEntity<>(postService.deleteById(postId), HttpStatus.ACCEPTED);
    }
}
