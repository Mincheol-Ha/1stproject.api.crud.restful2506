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

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentJpaRepository commentJpaRepository;
    private final PostJpaRepository postJpaRepository;

    @Transactional
    public void createComment(CommentRequestDto commentRequestDto) {
        PostEntity post = postJpaRepository.findById(commentRequestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없음"));

        CommentEntity comment = CommentEntity.builder()
                .content(commentRequestDto.getContent())
                .author(commentRequestDto.getAuthor())
                .post(post)   // ★ 여기가 반드시 필요함
                .build();

        commentJpaRepository.save(comment);
    }


    @Transactional
    public void updateComment(Integer id,@RequestBody CommentRequestDto commentRequestDto) {
        CommentEntity comment = commentJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다."));
    }

    @Transactional
    public void deleteComment(Integer id) {
        commentJpaRepository.deleteById(id);

    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findByPostId(Integer postId) {

        List<CommentEntity> list = commentJpaRepository.findByPostId(postId);

        return list.stream()
                .map(comment -> CommentResponseDto.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .author(comment.getAuthor())
                        .createdAt(comment.getCreateAt())
                        .build())
                .toList();
    }
}
