package com.postservice.postservice.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="like-service")
public interface FeignLike {
    @GetMapping("/postsOrComments/{postOrCommentId}/likes/count")
    public int likeCount( @PathVariable("postOrCommentId") String postOrCommentId);
}
