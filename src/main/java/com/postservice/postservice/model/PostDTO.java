package com.postservice.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDTO {
    @Id
    private String postID;
    @NotEmpty(message = "post is required")
    private String post;
    @NotEmpty(message = "postedBy ID is required")
    private String postedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int commentCounts;
    private int likeCounts;
}
