package com.example.mincheol1sr2.service;

import com.example.mincheol1sr2.dto.CommentRequestDto;
import com.example.mincheol1sr2.dto.CommentResponseDto;
import com.example.mincheol1sr2.entity.CommentEntity;
import com.example.mincheol1sr2.entity.PostEntity;
import com.example.mincheol1sr2.repository.CommentJpaRepository;
import com.example.mincheol1sr2.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentJpaRepository commentJpaRepository;
    private final PostJpaRepository postJpaRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        PostEntity post = postJpaRepository.findById(commentRequestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없음"));

        CommentEntity comment = CommentEntity.builder()
                .content(commentRequestDto.getContent())
                .author(commentRequestDto.getAuthor())
                .post(post)
                .build();

        CommentEntity savedComment = commentJpaRepository.save(comment);
        PostEntity savedPost = postJpaRepository.save(post);

        return CommentResponseDto.builder()
                .postId(post.getId())
                .content(savedComment.getContent())
                .author(savedComment.getAuthor())
                .createdAt(savedPost.getCreateAt())
                .build();
    }


    @Transactional
    public CommentResponseDto updateComment(Integer id,@RequestBody CommentRequestDto commentRequestDto) {
        CommentEntity comment = commentJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다."));

        comment.update(commentRequestDto.getContent());

        CommentEntity savedComment = commentJpaRepository.save(comment);

        return CommentResponseDto.builder()
                .postId(comment.getPost() != null ? comment.getPost().getId() : null)
                .content(comment.getContent())
                .updatedAt(comment.getUpdateAt())
                .message("댓글 수정 완료")
                .build();
    }

    @Transactional
    public CommentResponseDto deleteComment(Integer id) {
        commentJpaRepository.deleteById(id);

        return CommentResponseDto.builder()
                .message("삭제 되었습니다.")
                .build();

    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findByPostId(Integer postId) {

        // 1. 게시글 존재 체크 (없으면 예외 발생!)
        if (!postJpaRepository.existsById(postId)) {
            throw new NoSuchElementException("해당 게시글이 존재하지 않습니다.");
        }

        // 2. 댓글 조회
        List<CommentEntity> list = commentJpaRepository.findByPostId(postId);

        // 3. Dto 변환
        return list.stream()
                .map(comment -> CommentResponseDto.builder()
                        .postId(comment.getPost().getId())
                        .content(comment.getContent())
                        .author(comment.getAuthor())
                        .createdAt(comment.getCreateAt())
                        .build())
                .toList();
    }
}
