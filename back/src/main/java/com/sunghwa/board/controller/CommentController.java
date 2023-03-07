package com.sunghwa.board.controller;

import com.sunghwa.board.domain.Comment;
import com.sunghwa.board.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/api/comment/{postId}")
    @ResponseBody
    public ResponseEntity<List<Comment>> getComment(@PathVariable String postId) {
        return new ResponseEntity<List<Comment>>(commentService.findOne(Long.parseLong(postId)), HttpStatus.OK);
    }

    @PostMapping("/api/comment/add")
    public ResponseEntity<Comment> createComment(@RequestBody Map<String, String> payload) {
        Comment comment = new Comment(
            payload.get("writer"),
            payload.get("contents"),
            LocalDate.now(),
            Long.parseLong(payload.get("postId"))
        );

        return new ResponseEntity<Comment>(commentService.addComment(comment), HttpStatus.CREATED);
    }

    @PostMapping("/api/comment/config")
    public ResponseEntity<Comment> updateComment(@RequestBody Map<String, String> payload) {
        Comment comment = new Comment(
            Long.parseLong(payload.get("commentId")),
            payload.get("contents")
        );

        return new ResponseEntity<Comment>(commentService.updateComment(comment), HttpStatus.OK);
    }

    @PostMapping("/api/comment/delete")
    public ResponseEntity<Long> deleteComment(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Long>(commentService.deleteComment(Long.parseLong(payload.get("commentId"))), HttpStatus.OK);
    }
}
