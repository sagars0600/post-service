package com.postservice.postservice.service;

import com.postservice.postservice.Feign.FeignComment;
import com.postservice.postservice.Feign.FeignLike;
import com.postservice.postservice.Feign.FeignUser;
import com.postservice.postservice.exception.PostNotFoundException;
import com.postservice.postservice.model.Post;
import com.postservice.postservice.model.PostDTO;
import com.postservice.postservice.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;



import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PostService {
@Autowired
    private PostRepo postRepo;

@Autowired
private FeignComment feignComment;

@Autowired
private FeignLike feignLike;

@Autowired
private FeignUser feignUser;

    public Post savePost(Post post){
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        return postRepo.save(post);
    }

    public Optional<Post> findById(String postId) {

        Optional<Post> post = this.postRepo.findById(postId);
        if (!post.isPresent())
            throw new PostNotFoundException("Please check User Id");
        else return  post;

    }
    public Post updatePost(Post post, String postId){
        post.setUpdatedAt(LocalDateTime.now());
        return this.postRepo.save(post);
    }

    public String deleteById(String postId){
        this.postRepo.deleteById(postId);
        return "Post id "+postId+ " Deleted Successfully";
    }


    public List<PostDTO> allUser(Integer page, Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Pageable firstPage = PageRequest.of(page - 1, pageSize);
        List<Post> postModels = postRepo.findAll(firstPage).toList();

        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post postModel : postModels) {
            PostDTO postDTO = new PostDTO(postModel.getPostID(), postModel.getPost(),
                    feignUser.findByID(postModel.getPostedBy()).getFirstName(), postModel.getCreatedAt(),
                    postModel.getUpdatedAt(), feignLike.likeCount(postModel.getPostID()),
                    feignComment.commentCount(postModel.getPostID()));
            postDTOS.add(postDTO);
        }
        return postDTOS;


    }



}
