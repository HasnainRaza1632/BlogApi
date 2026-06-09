package com.practice.Blog.API.services;

import com.practice.Blog.API.DTO.CreateANew.CreateANewPost;
import com.practice.Blog.API.DTO.PostDTO;

import java.util.List;

public interface PostServices {

    PostDTO createAPostForAUser(Long userId, CreateANewPost createANewPost);

    List<PostDTO> getListOfAllPosts();

    PostDTO getPost(Long id);

    List<PostDTO> getAllPostsByUser(Long id);

    PostDTO updatePostById(Long id, CreateANewPost createANewPost);

    void deletePostById(Long id);

    List<PostDTO> sortingPostByCreatedAt();
}
