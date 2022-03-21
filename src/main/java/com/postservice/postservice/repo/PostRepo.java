package com.postservice.postservice.repo;

import com.postservice.postservice.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<Post,String> {
}
