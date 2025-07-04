package com.example.practiceonspringdatajpa.repository;

import com.example.practiceonspringdatajpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 🔍 Пошук за іменем
    List<User> findByName(String name);

    // 🔍 Пошук за email-доменом
    List<User> findByEmailEndingWith(String domain);
}
