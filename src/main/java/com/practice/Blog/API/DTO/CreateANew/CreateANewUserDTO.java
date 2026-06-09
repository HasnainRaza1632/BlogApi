package com.practice.Blog.API.DTO.CreateANew;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateANewUserDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String bio;

}
