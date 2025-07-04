package com.example.practiceonspringdatajpa.service;

import com.example.practiceonspringdatajpa.model.Post;
import com.example.practiceonspringdatajpa.model.User;
import com.example.practiceonspringdatajpa.repository.PostRepository;
import com.example.practiceonspringdatajpa.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // ✅ Створення користувача
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // ✅ Отримати користувача за ім'ям
    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    // ✅ Отримати користувачів по домену пошти
    public List<User> getUsersByEmailDomain(String domain) {
        return userRepository.findByEmailEndingWith(domain);
    }

    // ✅ Отримати всі пости користувача за userId
    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    // ✅ Створити користувача з постами в одній транзакції
    @Transactional
    public User createUserWithPosts(User user, List<Post> posts) {
        User savedUser = userRepository.save(user);

        for (Post post : posts) {
            post.setUser(savedUser);
            postRepository.save(post);
        }
        return savedUser;
    }
}
