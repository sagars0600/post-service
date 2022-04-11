package com.postservice.postservice.controller;

import com.postservice.postservice.model.Post;
import com.postservice.postservice.model.PostDTO;
import com.postservice.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@CrossOrigin(value = "*")
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;


    @PostMapping()
    public ResponseEntity<PostDTO> savePost(@RequestBody @Valid Post post){
        return  new ResponseEntity<>(postService.savePost(post), HttpStatus.ACCEPTED);

    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> findById(@PathVariable("postId") String postId){
        return new ResponseEntity<>(postService.findById(postId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody @Valid Post post, @PathVariable("postId") String postId){
        return  new ResponseEntity<>(postService.updatePost(post,postId), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deleteById(@PathVariable("postId") String postId){
        return  new ResponseEntity<>(postService.deleteById(postId), HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<List<PostDTO>> allUser(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize){
        return new ResponseEntity<>(postService.allUser(page,pageSize), HttpStatus.ACCEPTED);
    }

}
