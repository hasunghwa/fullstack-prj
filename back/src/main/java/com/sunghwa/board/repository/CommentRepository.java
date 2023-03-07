package com.sunghwa.board.repository;

import com.sunghwa.board.domain.Comment;

import java.util.List;

public interface CommentRepository {
    List<Comment> findByPostId(Long postId);
    Comment insert(Comment comment);
    Comment update(Comment comment);
    Long delete(Long commentId);
}
