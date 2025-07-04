package com.example.practiceonspringdatajpa.controller;

import com.example.practiceonspringdatajpa.model.Post;
import com.example.practiceonspringdatajpa.model.User;
import com.example.practiceonspringdatajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ 1. Створення нового користувача
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // ✅ 2. Отримання користувачів за ім'ям
    @GetMapping("/by-name")
    public List<User> getUsersByName(@RequestParam String name) {
        return userService.getUsersByName(name);
    }

    // ✅ 3. Отримання користувачів за доменом email
    @GetMapping("/by-domain")
    public List<User> getUsersByEmailDomain(@RequestParam String domain) {
        return userService.getUsersByEmailDomain(domain);
    }

    // ✅ 4. Отримання всіх постів по userId
    @GetMapping("/{userId}/posts")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return userService.getPostsByUserId(userId);
    }

    // ✅ 5. Створення користувача разом з постами (в одній транзакції)
    @PostMapping("/with-posts")
    public User createUserWithPosts(@RequestBody UserWithPostsRequest request) {
        return userService.createUserWithPosts(request.getUser(), request.getPosts());
    }

    // DTO клас для обробки JSON-запиту
    public static class UserWithPostsRequest {
        private User user;
        private List<Post> posts;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public List<Post> getPosts() {
            return posts;
        }

        public void setPosts(List<Post> posts) {
            this.posts = posts;
        }
    }
}
