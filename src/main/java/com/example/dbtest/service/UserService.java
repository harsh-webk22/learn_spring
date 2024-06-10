package com.example.dbtest.service;

import com.example.dbtest.entities.UserRepository;
import com.example.dbtest.entities.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Users saveUser(Users user){
        return userRepository.save(user);
    }

    public List<Users> getUser(){
        return userRepository.findAll();
    }

    public void followUser(Long followerId, Long followingId) {
        Optional<Users> followerOptional = userRepository.findById(followerId);
        Optional<Users> followingOptional = userRepository.findById(followingId);

        if (followerOptional.isPresent() && followingOptional.isPresent()) {
            Users follower = followerOptional.get();
            Users following = followingOptional.get();

            follower.getUsers().add(following);
            userRepository.save(follower);
        } else {
            throw new RuntimeException("User not found");
        }
    }

}
