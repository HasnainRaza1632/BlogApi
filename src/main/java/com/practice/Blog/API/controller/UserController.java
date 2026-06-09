package com.practice.Blog.API.controller;

import com.practice.Blog.API.DTO.CreateANew.CreateANewUserDTO;
import com.practice.Blog.API.DTO.UserDTO;
import com.practice.Blog.API.repository.UserRepository;
import com.practice.Blog.API.services.implementation.UserServicesImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServicesImplementation userServicesImplementation;
    private final UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createNewUser(@RequestBody @Valid CreateANewUserDTO createANewUserDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userServicesImplementation.Createauser(createANewUserDTO));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userServicesImplementation.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getOneUser(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(userServicesImplementation.getOneUser(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,@RequestBody @Valid CreateANewUserDTO createANewUserDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userServicesImplementation.updateUser(id,createANewUserDTO));
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userServicesImplementation.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
