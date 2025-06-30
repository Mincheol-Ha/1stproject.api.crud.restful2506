package com.example.mincheol1sr2.controller;

import com.example.mincheol1sr2.ApiResponse;
import com.example.mincheol1sr2.dto.CommentLikeDeleteRequestDto;
import com.example.mincheol1sr2.dto.CommentLikeDeleteResponseDto;
import com.example.mincheol1sr2.dto.CommentLikeRequestDto;
import com.example.mincheol1sr2.dto.CommentLikeResponseDto;
import com.example.mincheol1sr2.entity.CommentLikeEntity;
import com.example.mincheol1sr2.repository.CommentLikeRepository;
import com.example.mincheol1sr2.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping("/commentlikes")
    public CommentLikeResponseDto addLike(@RequestBody CommentLikeRequestDto commentLikeRequestDto) {
        return commentLikeService.addCommentLike(commentLikeRequestDto);
    }

    @DeleteMapping("/commentlikes")
    public CommentLikeDeleteResponseDto removeLike(@RequestBody CommentLikeDeleteRequestDto commentLikeDeleteRequestDto) {
        return commentLikeService.removeCommentLike(commentLikeDeleteRequestDto);
    }
}
