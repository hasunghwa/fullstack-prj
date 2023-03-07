package com.sunghwa.board.controller;

import com.sunghwa.board.domain.Post;
import com.sunghwa.board.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/posts")
    @ResponseBody
    public ResponseEntity<List<Post>> getAllPosts() {
        return new ResponseEntity<List<Post>>(postService.allPosts(), HttpStatus.OK);
    }

    @GetMapping("/api/post/{postId}")
    @ResponseBody
    public ResponseEntity<Optional<Post>> getPost(@PathVariable String postId) {
        return new ResponseEntity<Optional<Post>>(postService.findOne(Long.parseLong(postId)), HttpStatus.OK);
    }

    @PostMapping("/api/post/add")
    public ResponseEntity<Post> createPost(@RequestBody Map<String, String> payload) {
        Post post = new Post(
            payload.get("writer"),
            payload.get("title"),
            payload.get("contents"),
            LocalDate.now(),
            0
        );

        return new ResponseEntity<Post>(postService.writePost(post), HttpStatus.CREATED);
    }

    @PostMapping("/api/post/config")
    public ResponseEntity<Post> configPost(@RequestBody Map<String, String> payload) {
        Post post = new Post(
            Long.parseLong(payload.get("postId")),
            payload.get("title"),
            payload.get("contents")
        );

        return new ResponseEntity<Post>(postService.updatePost(post), HttpStatus.OK);
    }

    @PostMapping("/api/post/delete")
    public ResponseEntity<Long> deletePost(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Long>(postService.deletePost(Long.parseLong(payload.get("postId"))), HttpStatus.OK);
    }
}
