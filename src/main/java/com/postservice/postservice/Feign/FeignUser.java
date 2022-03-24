package com.postservice.postservice.Feign;

import com.postservice.postservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service")
public interface FeignUser {
    @GetMapping("/{userId}")
    public User findByID(@PathVariable("userId") String userId);
}
