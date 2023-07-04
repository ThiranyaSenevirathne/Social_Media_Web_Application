package com.foodies.foodiesbackend.service.impl;


import com.foodies.foodiesbackend.DTO.ApiResponse;
import com.foodies.foodiesbackend.DTO.PostDto;
import com.foodies.foodiesbackend.entity.Post;
import com.foodies.foodiesbackend.entity.User;
import com.foodies.foodiesbackend.repository.PostRepository;
import com.foodies.foodiesbackend.repository.UserRepository;
import com.foodies.foodiesbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ApiResponse savePost(int id, PostDto postDto) throws IOException {
        Post post1 = new Post();

        //post1.findPostByUpk(id);
        User user = userRepository.findUserById(id);
        post1.setUser(user);
        post1.setPostDescription(postDto.getPostDescription());
        byte[] imageContent = postDto.getImage().getBytes();
        post1.setImage(imageContent);

        postRepository.save(post1);
        return new ApiResponse(200, "post added successfully", false);
    }

    @Override
    public List<Post> getUserPosts(int uid) {
            return  postRepository.findAllByUser_Id(uid);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

}
