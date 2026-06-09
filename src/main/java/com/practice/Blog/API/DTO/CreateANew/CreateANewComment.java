package com.practice.Blog.API.DTO.CreateANew;

import com.practice.Blog.API.entity.Post;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateANewComment {
    @NotBlank
    private String text;
}
