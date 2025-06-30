package com.example.mincheol1sr2.controller;

import com.example.mincheol1sr2.dto.*;
import com.example.mincheol1sr2.repository.UserJpaRepository;
import com.example.mincheol1sr2.service.PostService;
import com.example.mincheol1sr2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final UserService userService;
    private final PostService postService;

    @PostMapping("/signup")
    public SignupResponseDto Signup(@RequestBody SignupRequestDto signupRequestDto) {
       return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
       return userService.login(loginRequestDto);
    }

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity updatePost(@PathVariable Integer postId, @RequestBody PostRequestDto postRequestDto) {
        postService.updatePost(postId, postRequestDto);
        return  ResponseEntity.ok("수정 되었습니다.");
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("게시글 삭제");
    }
}
