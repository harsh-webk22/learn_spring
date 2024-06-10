package com.example.dbtest.controller;


import com.example.dbtest.entities.Post;
import com.example.dbtest.entities.UserRepository;
import com.example.dbtest.entities.Users;
import com.example.dbtest.service.PostService;
import com.example.dbtest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@CacheConfig(cacheNames = "postCache")
public class PostController {
    private PostService postService;
    private UserRepository userRepository;

    @Autowired
    private RedisService redisService;

    PostController(PostService postService, UserRepository userRepository){
        this.postService = postService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public List<Post> getPosts(@PathVariable Long id){
        return postService.getAllPost(id);
    }

    @PostMapping("/")
    public Post savePost(@RequestBody PostDto post) throws Exception {
        String content  = post.getContent();
        String userId  = post.getUser_id();


        Optional<Users> user = userRepository.findById(Long.parseLong(userId));

        if(user.isPresent()){
            Post newPost = new Post( content , user.get());
            return postService.savePost(newPost);
        } else {
            throw new Exception("User not found");
        }
    }


    @GetMapping("/users/{userId}/following/posts")
    public List<Post> getTop10PostsFromFollowedUsers(@PathVariable Long userId) {
        List<Post> ans = redisService.get(Long.toString(userId), List.class);

        if(ans!=null){
            System.out.println("Serving from cache");
            return  ans;
        } else {
            ans = postService.getTop10PostsFromFollowedUsers(userId);
            redisService.set(Long.toString(userId), ans, 300l);
        }
        return ans;
    }

}
