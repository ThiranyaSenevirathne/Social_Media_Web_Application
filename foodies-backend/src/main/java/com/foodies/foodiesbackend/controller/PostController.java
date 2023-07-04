package com.foodies.foodiesbackend.controller;


import com.foodies.foodiesbackend.DTO.ApiResponse;
import com.foodies.foodiesbackend.DTO.PostDto;
import com.foodies.foodiesbackend.entity.Post;
import com.foodies.foodiesbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class PostController {

    @Autowired
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts/{id}")
    public ApiResponse savePost(@PathVariable int id, @ModelAttribute PostDto postDto) throws IOException {
        return postService.savePost(id, postDto);
    }

    @GetMapping( "/posts/{id}")
    public List<Post> getUserPosts(@PathVariable int id) {
        return postService.getUserPosts(id);
    }

    @GetMapping( "/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}
