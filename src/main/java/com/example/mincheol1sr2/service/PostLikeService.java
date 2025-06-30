package com.example.mincheol1sr2.service;

import com.example.mincheol1sr2.dto.PostLikeRequestDto;
import com.example.mincheol1sr2.dto.PostLikeResponseDto;
import com.example.mincheol1sr2.entity.CommentLikeEntity;
import com.example.mincheol1sr2.entity.PostEntity;
import com.example.mincheol1sr2.entity.PostLikeEntity;
import com.example.mincheol1sr2.entity.UserEntity;
import com.example.mincheol1sr2.repository.PostJpaRepository;
import com.example.mincheol1sr2.repository.PostLikeRepository;
import com.example.mincheol1sr2.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;


    @Transactional
    public PostLikeResponseDto addLike(PostLikeResponseDto postLikeResponseDto) {
        UserEntity user = userJpaRepository.findById(postLikeResponseDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        PostEntity post = postJpaRepository.findById(postLikeResponseDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        String message = "좋아요가 성공적으로 등록되었습니다.";

        if(postLikeRepository.existsByUserAndPost(user, post)) {
            throw new IllegalArgumentException("이미 좋아요 등록이 되었습니다.");
        }

        PostLikeEntity saved = postLikeRepository.save(PostLikeEntity.builder()
                .user(user)
                .post(post)
                .build());

       return PostLikeResponseDto.builder()
               .postId(post.getId())
               .userId(user.getId())
               .createdAt(saved.getCreateAt())
               .message(message)
               .build();
    }

    @Transactional
    public void removeLike(Integer postId, PostLikeRequestDto postLikeRequestDto) {
        UserEntity user = userJpaRepository.findById(postLikeRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        PostEntity  post = postJpaRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        postLikeRepository.deleteByUserAndPost(user, post);
    }

    public Integer countLikes(Integer postId) {
        PostEntity post = postJpaRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        return postLikeRepository.countByPost(post);

    }



}
