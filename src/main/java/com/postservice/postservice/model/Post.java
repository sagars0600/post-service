package com.postservice.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class Post {
    @Id
    private String postID;



    @NotEmpty(message = "user ID is required")
    private String userID;
    @NotEmpty(message = "post data is required")
    private String post;
    @NotEmpty(message = " Name is required")
    private String postedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
