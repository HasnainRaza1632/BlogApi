package com.practice.Blog.API.services;

import com.practice.Blog.API.DTO.CommentDTO;
import com.practice.Blog.API.DTO.CreateANew.CreateANewComment;
import com.practice.Blog.API.services.implementation.CommentServicesImplementation;

import java.util.List;

public interface CommentServices {
    CommentDTO addCommentToPost(Long postId, CreateANewComment createANewComment);

    List<CommentDTO> getAllCommentWithPostId(Long postId);

    void deleteCommentWithId(Long id);
}
