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

    @PostMapping("/likes")
    public PostLikeResponseDto addLike(@RequestBody PostLikeResponseDto postLikeResponseDto) {
        return postLikeService.addLike(postLikeResponseDto);
    }

    @DeleteMapping("/{postId}/likes")
    public ResponseEntity<?> removeLike(
            @PathVariable Integer postId,
            @RequestBody PostLikeRequestDto postLikeRequestDto) {

        postLikeService.removeLike(postId, postLikeRequestDto);
        return ResponseEntity.ok("좋아요 취소!");
    }

    // 좋아요 개수 조회
    @GetMapping("/{postId}/likes/count")
    public ResponseEntity<Integer> countLikes(@PathVariable Integer postId) {
        int count = postLikeService.countLikes(postId);
        return ResponseEntity.ok(count);
    }
}
