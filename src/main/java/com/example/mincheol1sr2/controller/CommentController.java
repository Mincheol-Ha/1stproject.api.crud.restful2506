package com.example.mincheol1sr2.controller;

import com.example.mincheol1sr2.dto.CommentRequestDto;
import com.example.mincheol1sr2.dto.CommentResponseDto;
import com.example.mincheol1sr2.repository.CommentJpaRepository;
import com.example.mincheol1sr2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(commentRequestDto);
    }

    @GetMapping("/comments/post/{postId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByPost(@PathVariable Integer postId) {
        List<CommentResponseDto> comments = commentService.findByPostId(postId);
        return ResponseEntity.ok(comments);

    }

    @PutMapping("/comments/{id}")
    public CommentResponseDto updateComment(@PathVariable Integer id, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(id, commentRequestDto);
    }

    @DeleteMapping("/comments/{id}")
    public CommentResponseDto deleteComment(@PathVariable Integer id)  {
        return commentService.deleteComment(id);
    }




}

