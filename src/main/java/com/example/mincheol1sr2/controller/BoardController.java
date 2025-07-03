package com.example.mincheol1sr2.controller;

import com.example.mincheol1sr2.dto.*;
import com.example.mincheol1sr2.repository.UserJpaRepository;
import com.example.mincheol1sr2.service.PostService;
import com.example.mincheol1sr2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

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
    public PostResponseDto updatePost(@PathVariable Integer postId, @RequestBody PostRequestDto postRequestDto) {
       return postService.updatePost(postId, postRequestDto);
    }

    @DeleteMapping("/posts/{postId}")
    public PostResponseDto deletePost(@PathVariable Integer postId) {
        return postService.deletePost(postId);
    }
}
