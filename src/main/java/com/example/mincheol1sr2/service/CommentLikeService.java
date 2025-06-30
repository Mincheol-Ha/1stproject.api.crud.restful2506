package com.example.mincheol1sr2.service;

import com.example.mincheol1sr2.dto.*;
import com.example.mincheol1sr2.entity.CommentEntity;
import com.example.mincheol1sr2.entity.CommentLikeEntity;
import com.example.mincheol1sr2.entity.PostEntity;
import com.example.mincheol1sr2.entity.UserEntity;
import com.example.mincheol1sr2.repository.CommentJpaRepository;
import com.example.mincheol1sr2.repository.CommentLikeRepository;
import com.example.mincheol1sr2.repository.PostJpaRepository;
import com.example.mincheol1sr2.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentJpaRepository commentJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final PostJpaRepository postJpaRepository;

    @Transactional
    public CommentLikeResponseDto addCommentLike(CommentLikeRequestDto commentLikeRequestDto) {
        UserEntity user = userJpaRepository.findById(commentLikeRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        CommentEntity comment = commentJpaRepository.findById(commentLikeRequestDto.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음"));

        PostEntity post = postJpaRepository.findById(commentLikeRequestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));


        if (commentLikeRepository.existsByUserAndCommentAndPost(user, comment, post)) {
            throw new IllegalArgumentException("이미 좋아요를 눌렀습니다.");
        }

        // 저장하고, 반환값(saved)에 createAt이 들어감!
        CommentLikeEntity saved = commentLikeRepository.save(CommentLikeEntity.builder()
                .user(user)
                .comment(comment)
                .post(post)
                .build());

        // 등록 성공 시, 응답 DTO 생성해서 리턴
        return CommentLikeResponseDto.builder()
                .userId(user.getId())
                .commentId(comment.getId())
                .postId(post.getId())
                .createAt(saved.getCreateAt())
                .build();
    }

    @Transactional
    public CommentLikeDeleteResponseDto removeCommentLike(CommentLikeDeleteRequestDto commentLikeDeleteRequestDto) {

        CommentEntity comment = commentJpaRepository.findById(commentLikeDeleteRequestDto.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음"));

        PostEntity post = postJpaRepository.findById(commentLikeDeleteRequestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        commentLikeRepository.deleteByCommentAndPost(comment, post);
        String message = "댓글 좋아요가 성공적으로 삭제되었습니다.";

        return CommentLikeDeleteResponseDto.builder()
                .commentId(comment.getId())
                .postId(post.getId())
                .message(message)
                .build();
    }

}
