package com.postservice.postservice.controller;

import com.postservice.postservice.model.Post;
import com.postservice.postservice.model.PostDTO;
import com.postservice.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class PostController {
    @Autowired
    private PostService postService;



    @PostMapping("/posts")
    public ResponseEntity<Post> savePost(@RequestBody @Valid Post post){
        return  new ResponseEntity<>(postService.savePost(post), HttpStatus.ACCEPTED);
    }



    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findById(@PathVariable("postId") String postId) {
        Optional<Post> post = postService.findById(postId);
        return new ResponseEntity(post, HttpStatus.ACCEPTED);
    }


    @PutMapping("/posts/{postId}")
    public ResponseEntity<Post> updatePost(@RequestBody @Valid Post post, @PathVariable("postId") String postId){
        return  new ResponseEntity<>(postService.updatePost(post,postId), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deleteById(@PathVariable("postId") String postId){
        return  new ResponseEntity<>(postService.deleteById(postId), HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<List<PostDTO>> allUser(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize){
        return new ResponseEntity<>(postService.allUser(page,pageSize), HttpStatus.ACCEPTED);
    }

}
