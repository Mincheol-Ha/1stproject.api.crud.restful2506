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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;

//    @Transactional
//    public PostLikeResponseDto addLike(Integer postId) {
//        // 1. JWT에서 현재 사용자 email 추출
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserEmail = auth.getName();
//
//        // 2. email로 UserEntity 조회
//        UserEntity user = userJpaRepository.findByEmail(currentUserEmail)
//                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
//
//        // 3. postId로 게시글 조회
//        PostEntity post = postJpaRepository.findById(postId)
//                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
//
//        // 4. 이미 좋아요 등록 여부 확인
//        if(postLikeRepository.existsByUserAndPost(user, post)) {
//            throw new IllegalArgumentException("이미 좋아요가 등록되어 있습니다.");
//        }
//
//        // 5. 저장 및 응답
//        PostLikeEntity saved = postLikeRepository.save(
//                PostLikeEntity.builder()
//                        .user(user)
//                        .post(post)
//                        .build()
//        );
//
//        return PostLikeResponseDto.builder()
//                .userId(user.getId())
//                .postId(post.getId())
//                .createdAt(saved.getCreateAt())
//                .message("좋아요가 등록되었습니다.")
//                .build();
//    }

    @Transactional
    public PostLikeResponseDto toggleLike(Integer postId) {
        // 1. 현재 로그인 사용자 email
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = auth.getName();

        // 2. 유저, 게시글 엔티티 조회
        UserEntity user = userJpaRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        PostEntity post = postJpaRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        // 3. 이미 좋아요가 있는지 체크
        Optional<PostLikeEntity> likeOpt = postLikeRepository.findByUserAndPost(user, post);
        String message;

        if (likeOpt.isPresent()) {
            // 이미 좋아요 있음 → 삭제(취소)
            postLikeRepository.delete(likeOpt.get());
            message = "좋아요가 취소되었습니다.";
        } else {
            // 좋아요 없음 → 추가
            PostLikeEntity saved = postLikeRepository.save(
                    PostLikeEntity.builder().user(user).post(post).build());
            message = "좋아요가 등록되었습니다.";
        }

        // 4. 응답 Dto
        return PostLikeResponseDto.builder()
                .postId(post.getId())
                .userId(user.getId())
                .message(message)
                .build();
    }

    @Transactional(readOnly = true)
    public PostLikeResponseDto countLikes(Integer postId) {

        PostEntity post = postJpaRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        long likeCount = postLikeRepository.countByPost(post);

        return PostLikeResponseDto.builder()
                .postId(post.getId())
                .count(likeCount)
                .build();

    }
}
