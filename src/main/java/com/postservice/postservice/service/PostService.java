package com.postservice.postservice.service;

import com.postservice.postservice.Feign.FeignComment;
import com.postservice.postservice.Feign.FeignLike;
import com.postservice.postservice.Feign.FeignUser;
import com.postservice.postservice.constfiles.ConstFile;
import com.postservice.postservice.exception.PostNotFoundException;
import com.postservice.postservice.model.Post;
import com.postservice.postservice.model.PostDTO;
import com.postservice.postservice.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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

    public PostDTO savePost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        postRepo.save(post);
        PostDTO postDTO = new PostDTO(post.getPostID(), post.getPost(),
                feignUser.findByID(post.getPostedBy()),
                feignLike.likeCount(post.getPostID()),
                feignComment.commentCount(post.getPostID()),
                post.getCreatedAt(), post.getUpdatedAt());
        return postDTO;
    }

    public PostDTO findById(String postId) {

        Post postModel = postRepo.findById(postId).get();

        PostDTO postDTO = new PostDTO(postModel.getPostID(), postModel.getPost(),
                feignUser.findByID(postModel.getPostedBy()),
                feignLike.likeCount(postModel.getPostID()),
                feignComment.commentCount(postModel.getPostID()),
                postModel.getCreatedAt(), postModel.getUpdatedAt());
        return postDTO;

    }

    public PostDTO updatePost(Post post, String postId) {
        if (postRepo.findById(postId).isPresent()) {
            post.setPostID(postId);
            post.setUpdatedAt(LocalDateTime.now());
            post.setCreatedAt(postRepo.findById(postId).get().getCreatedAt());
            postRepo.save(post);
            PostDTO postDto = new PostDTO(post.getPostID(), post.getPost(),
                    feignUser.findByID(post.getPostedBy()),
                    feignLike.likeCount(post.getPostID()),
                    feignComment.commentCount(post.getPostID()),
                    post.getCreatedAt(), post.getUpdatedAt());
            return postDto;
        } else {
            throw new PostNotFoundException(ConstFile.update);
        }
    }
        public String deleteById (String postId){
            if (postRepo.findById(postId).isPresent()) {
                this.postRepo.deleteById(postId);
                return ConstFile.deleted;
            } else {
                throw new PostNotFoundException(ConstFile.ids);
            }

        }


        public List<PostDTO> allUser (Integer page, Integer pageSize){
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
                        feignUser.findByID(postModel.getPostedBy()),
                        feignComment.commentCount(postModel.getPostID()),
                        feignLike.likeCount(postModel.getPostID()),
                        postModel.getCreatedAt(), postModel.getUpdatedAt()
                );
                postDTOS.add(postDTO);
            }
            return postDTOS;


        }


    }

