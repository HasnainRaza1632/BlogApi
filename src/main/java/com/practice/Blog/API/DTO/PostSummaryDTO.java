package com.practice.Blog.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSummaryDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}