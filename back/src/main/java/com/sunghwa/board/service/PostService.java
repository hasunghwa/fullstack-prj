package com.sunghwa.board.service;

import com.sunghwa.board.domain.Post;
import com.sunghwa.board.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post writePost(Post post) {
        postRepository.insert(post);
        return post;
    }

    public List<Post> allPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findOne(Long postId) {
        return postRepository.findById(postId);
    }

    public Post updatePost(Post post) {
        postRepository.update(post);
        return post;
    }

    public Long deletePost(Long postId) {
        return postRepository.delete(postId);
    }
}

