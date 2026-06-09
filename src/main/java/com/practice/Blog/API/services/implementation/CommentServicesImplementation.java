package com.practice.Blog.API.services.implementation;

import com.practice.Blog.API.DTO.CommentDTO;
import com.practice.Blog.API.DTO.CreateANew.CreateANewComment;
import com.practice.Blog.API.DTO.PostSummaryDTO;
import com.practice.Blog.API.entity.Comment;
import com.practice.Blog.API.entity.Post;
import com.practice.Blog.API.exceptions.ResourceNotFoundException;
import com.practice.Blog.API.repository.CommentRepository;
import com.practice.Blog.API.repository.PostRepository;
import com.practice.Blog.API.services.CommentServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServicesImplementation implements CommentServices {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    @Transactional
    @Override
    public CommentDTO addCommentToPost(Long postId, CreateANewComment createANewComment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with id:"+postId));
        Comment comment = modelMapper.map(createANewComment,Comment.class);
        comment.setPost(post);
        post.getComments().add(comment);
        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public List<CommentDTO> getAllCommentWithPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with id:"+postId));
        return post.getComments().stream().map((element) -> modelMapper.map(element, CommentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCommentWithId(Long id) {
        if(!commentRepository.existsById(id)){
            throw new ResourceNotFoundException("Comment not found with id:"+id);
        }
        commentRepository.deleteById(id);
    }
}
