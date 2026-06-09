package com.practice.Blog.API.services.implementation;

import com.practice.Blog.API.DTO.AuthorSummaryDTO;
import com.practice.Blog.API.DTO.CreateANew.CreateANewPost;
import com.practice.Blog.API.DTO.PostDTO;
import com.practice.Blog.API.entity.Post;
import com.practice.Blog.API.entity.User;
import com.practice.Blog.API.exceptions.ResourceNotFoundException;
import com.practice.Blog.API.repository.PostRepository;
import com.practice.Blog.API.repository.UserRepository;
import com.practice.Blog.API.services.PostServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServicesImplementation implements PostServices {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public PostDTO createAPostForAUser(Long userId, CreateANewPost createANewPost) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with id:"+userId));
        Post post = modelMapper.map(createANewPost,Post.class);
        post.setAuthor(user);
        user.getPosts().add(post); //For bioDirectional relation

        AuthorSummaryDTO authorSummaryDTO = modelMapper.map(user,AuthorSummaryDTO.class);
        PostDTO postDTO = modelMapper.map(post,PostDTO.class);
        postDTO.setAuthor(authorSummaryDTO);
        return postDTO;
    }

    @Override
    public List<PostDTO> getListOfAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts
                .stream()
                .map((element) -> modelMapper.map(element, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPost(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with id:"+id));
        return modelMapper.map(post,PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPostsByUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id:"+id));
        return user.getPosts().stream().map((element) -> modelMapper.map(element, PostDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PostDTO updatePostById(Long id, CreateANewPost createANewPost) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with id:"+id));
        modelMapper.map(createANewPost,post);
        return modelMapper.map(post,PostDTO.class);
    }

    @Override
    public void deletePostById(Long id) {
        if(!postRepository.existsById(id)){
            throw new ResourceNotFoundException("Post not found with id:"+id);
        }
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDTO> sortingPostByCreatedAt() {
        List<Post> posts = postRepository.findAll();
        posts.sort(Comparator.comparing(Post::getCreatedAt).reversed());
        return posts.stream().map((element) -> modelMapper.map(element, PostDTO.class)).collect(Collectors.toList());
    }
}
