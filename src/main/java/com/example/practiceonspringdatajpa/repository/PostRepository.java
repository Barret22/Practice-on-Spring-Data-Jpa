package com.example.practiceonspringdatajpa.repository;

import com.example.practiceonspringdatajpa.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 🔍 Знайти всі пости користувача за userId
    List<Post> findByUserId(Long userId);
}
