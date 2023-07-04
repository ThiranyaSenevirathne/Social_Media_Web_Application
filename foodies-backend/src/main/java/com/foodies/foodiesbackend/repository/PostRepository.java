package com.foodies.foodiesbackend.repository;

import com.foodies.foodiesbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByUser_Id(int id);

    Post findPostById(int id);
}
