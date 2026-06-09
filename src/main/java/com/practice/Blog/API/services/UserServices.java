package com.practice.Blog.API.services;

import com.practice.Blog.API.DTO.CreateANew.CreateANewUserDTO;
import com.practice.Blog.API.DTO.UserDTO;

import java.util.List;

public interface UserServices {
    UserDTO Createauser(CreateANewUserDTO createANewUserDTO);

    List<UserDTO> getAllUsers();

    UserDTO getOneUser(Long id);

    UserDTO updateUser(Long id, CreateANewUserDTO createANewUserDTO);

    void deleteUser(Long id);
}
