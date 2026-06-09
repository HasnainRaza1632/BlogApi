package com.practice.Blog.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSummaryDTO {
    private Long id;
    private String name;
    private String email;
    private String bio;

}
