package com.example.vegan.domain.post.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRequest {
    private Long postId;
    private Long imageId;
    private String key;
}
