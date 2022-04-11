package com.postservice.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeignRequest {
    private Post postModel;
    private User user;
    private int commentCounts;
    private int likeCounts;
}
