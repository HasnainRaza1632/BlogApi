package com.practice.Blog.API.repository;

import com.practice.Blog.API.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}