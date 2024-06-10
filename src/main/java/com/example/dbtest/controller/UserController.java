package com.example.dbtest.controller;

import com.example.dbtest.entities.Users;
import com.example.dbtest.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public List<Users> getUser(){
        return userService.getUser();
    }

    @PostMapping("/")
    public Users saveUser(@RequestBody Users user){
        return  userService.saveUser(user);
    }

    @PostMapping("/{followerId}/follow/{followingId}")
    public void followUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        userService.followUser(followerId, followingId);
    }
}
