package com.postservice.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "postedBy ID is required")
    private User postedBy;
    private int likeCounts;
    private int commentCounts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
