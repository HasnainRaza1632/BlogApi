package com.practice.Blog.API.DTO.CreateANew;

import com.practice.Blog.API.entity.Comment;
import com.practice.Blog.API.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateANewPost {
    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
