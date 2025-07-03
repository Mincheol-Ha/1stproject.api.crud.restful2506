package com.example.mincheol1sr2.service;

import com.example.mincheol1sr2.dto.*;
import com.example.mincheol1sr2.entity.*;
import com.example.mincheol1sr2.repository.CommentJpaRepository;
import com.example.mincheol1sr2.repository.CommentLikeRepository;
import com.example.mincheol1sr2.repository.PostJpaRepository;
import com.example.mincheol1sr2.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentJpaRepository commentJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final PostJpaRepository postJpaRepository;

//    @Transactional
//    public CommentLikeResponseDto addCommentLike(CommentLikeRequestDto commentLikeRequestDto) {
//        UserEntity user = userJpaRepository.findById(commentLikeRequestDto.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
//
//        CommentEntity comment = commentJpaRepository.findById(commentLikeRequestDto.getCommentId())
//                .orElseThrow(() -> new IllegalArgumentException("댓글 없음"));
//
//        PostEntity post = postJpaRepository.findById(commentLikeRequestDto.getPostId())
//                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
//
//
//        if (commentLikeRepository.existsByUserAndCommentAndPost(user, comment, post)) {
//            throw new IllegalArgumentException("이미 좋아요를 눌렀습니다.");
//        }
//
//        // 저장하고, 반환값(saved)에 createAt이 들어감!
//        CommentLikeEntity saved = commentLikeRepository.save(CommentLikeEntity.builder()
//                .user(user)
//                .comment(comment)
//                .post(post)
//                .build());
//
//        // 등록 성공 시, 응답 DTO 생성해서 리턴
//        return CommentLikeResponseDto.builder()
//                .userId(user.getId())
//                .commentId(comment.getId())
//                .postId(post.getId())
//                .createAt(saved.getCreateAt())
//                .build();
//    }

    @Transactional
    public CommentLikeResponseDto toggleCommentLike(Integer commentId) {
        // 1. 현재 로그인 사용자 email
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = auth.getName();

        // 2. 유저, 댓글 엔티티 조회
        UserEntity user = userJpaRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        CommentEntity comment = commentJpaRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음"));

        // 3. 이미 좋아요가 있는지 체크 (Repository에 추가 필요)
        Optional<CommentLikeEntity> likeOpt = commentLikeRepository.findByUserAndComment(user, comment);

        String message;
        if (likeOpt.isPresent()) {
            // 이미 좋아요 있음 → 삭제(취소)
            commentLikeRepository.delete(likeOpt.get());
            message = "댓글 좋아요가 취소되었습니다.";
        } else {
            // 좋아요 없음 → 추가
            commentLikeRepository.save(CommentLikeEntity.builder()
                    .user(user)
                    .comment(comment)
                    .build());
            message = "댓글 좋아요가 등록되었습니다.";
        }

        // 4. 응답 Dto 반환
        return CommentLikeResponseDto.builder()
                .commentId(comment.getId())
                .userId(user.getId())
                .message(message)
                .postId(comment.getPost().getId())
                .build();
    }

}
