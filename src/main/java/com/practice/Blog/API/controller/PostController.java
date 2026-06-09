package com.practice.Blog.API.controller;

import com.practice.Blog.API.DTO.CreateANew.CreateANewPost;
import com.practice.Blog.API.DTO.PostDTO;
import com.practice.Blog.API.entity.Post;
import com.practice.Blog.API.services.implementation.PostServicesImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostServicesImplementation postServicesImplementation;

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<PostDTO> createAPostForAUser(@PathVariable Long userId, @RequestBody @Valid CreateANewPost createANewPost){
        return ResponseEntity.status(HttpStatus.CREATED).body(postServicesImplementation.createAPostForAUser(userId,createANewPost));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getListOfAllPosts(){
        return ResponseEntity.ok(postServicesImplementation.getListOfAllPosts());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id){
        return ResponseEntity.ok(postServicesImplementation.getPost(id));
    }

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<PostDTO>> getAllPostsByUser(@PathVariable Long id){
        return ResponseEntity.ok(postServicesImplementation.getAllPostsByUser(id));
    }

    @PostMapping("/posts/{id}")
    public ResponseEntity<PostDTO> updatePostById(@PathVariable Long id ,@RequestBody @Valid CreateANewPost createANewPost){
        return ResponseEntity.status(HttpStatus.CREATED).body(postServicesImplementation.updatePostById(id,createANewPost));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id){
        postServicesImplementation.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts/createdAt")
    public ResponseEntity<List<PostDTO>> sortingPostByCreatedAt(){
        return ResponseEntity.ok(postServicesImplementation.sortingPostByCreatedAt());
    }

}
