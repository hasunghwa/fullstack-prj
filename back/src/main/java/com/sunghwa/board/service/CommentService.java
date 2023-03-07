package com.sunghwa.board.service;

import com.sunghwa.board.domain.Comment;
import com.sunghwa.board.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Comment comment) {
        commentRepository.insert(comment);
        return comment;
    }

    public List<Comment> findOne(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment updateComment(Comment comment) {
        commentRepository.update(comment);
        return comment;
    }

    public Long deleteComment(Long commentId) {
        commentRepository.delete(commentId);
        return commentId;
    }
}
