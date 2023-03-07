package com.sunghwa.board.repository;

import com.sunghwa.board.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> findAll();
    Optional<Post> findById(Long postId);
    Post insert(Post post);
    Post update(Post post);
    Long delete(Long postId);
}
