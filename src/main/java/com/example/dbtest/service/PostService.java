package com.example.dbtest.service;
import com.example.dbtest.entities.Post;
import com.example.dbtest.entities.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;

    PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Post savePost(Post post){
        return postRepository.save(post);
    }

    public List<Post> getAllPost(Long id) {
        return postRepository.findByUserId(id);
    }

    public List<Post> getTop10PostsFromFollowedUsers(Long userId) {
        return postRepository.findTop10PostsFromFollowedUsers(userId);
    }
}
