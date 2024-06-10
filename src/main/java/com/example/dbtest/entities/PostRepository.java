package com.example.dbtest.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);

    @Query("SELECT p FROM Post p WHERE p.user IN (SELECT u FROM Users u JOIN u.users followers WHERE followers.id = :userId) ORDER BY p.id DESC")
    List<Post> findTop10PostsFromFollowedUsers(Long userId);
}
