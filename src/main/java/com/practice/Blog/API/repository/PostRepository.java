package com.practice.Blog.API.repository;

import com.practice.Blog.API.entity.Post;
import com.practice.Blog.API.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}