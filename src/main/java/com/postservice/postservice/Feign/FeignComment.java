package com.postservice.postservice.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "comment-user")
public interface FeignComment {
    @GetMapping("/posts/{postId}/comments/count")
    public int commentCount(@PathVariable("postId") String postId);
}
