package com.prosigliere.codingchallenge.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostDTO {
    private Long id;
    private String title;
    private String content;
    private List<CommentDTO> comments;
}
