package com.practice.Blog.API.controller;

import com.practice.Blog.API.DTO.CommentDTO;
import com.practice.Blog.API.DTO.CreateANew.CreateANewComment;
import com.practice.Blog.API.services.implementation.CommentServicesImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentServicesImplementation commentServicesImplementation;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> addCommentToPost(@PathVariable Long postId , @RequestBody @Valid CreateANewComment createANewComment){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentServicesImplementation.addCommentToPost(postId,createANewComment));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getAllCommentWithPostId(@PathVariable Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(commentServicesImplementation.getAllCommentWithPostId(postId));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteCommentWithId(@PathVariable Long id){
        commentServicesImplementation.deleteCommentWithId(id);
        return ResponseEntity.noContent().build();
    }
}
