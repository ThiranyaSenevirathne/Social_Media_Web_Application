package com.foodies.foodiesbackend.service;

import com.foodies.foodiesbackend.DTO.ApiResponse;
import com.foodies.foodiesbackend.DTO.PostDto;
import com.foodies.foodiesbackend.entity.Post;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface PostService {
    ApiResponse savePost(int id, PostDto postDto) throws IOException;

    List<Post> getUserPosts(int id);

    List<Post> getAllPosts();
}
