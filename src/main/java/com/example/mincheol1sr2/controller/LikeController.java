package com.example.mincheol1sr2.controller;

import com.example.mincheol1sr2.dto.PostLikeRequestDto;
import com.example.mincheol1sr2.dto.PostLikeResponseDto;
import com.example.mincheol1sr2.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/likes/{postId}")
    public PostLikeResponseDto addLike(@PathVariable Integer postId) {
        return postLikeService.addLike(postId);
    }

    @PostMapping("/togglelikes/{postId}")
    public PostLikeResponseDto toggleLike(@PathVariable Integer postId) {
        return postLikeService.toggleLike(postId);
    }

    // 좋아요 개수 조회
    @GetMapping("/{postId}/likes/count")
    public PostLikeResponseDto countLikes(@PathVariable Integer postId) {
        return postLikeService.countLikes(postId);

    }
}
