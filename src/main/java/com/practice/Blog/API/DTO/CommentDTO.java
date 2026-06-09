package com.practice.Blog.API.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
        private Long id;
        private String text;
        private LocalDateTime createdAt;
}
