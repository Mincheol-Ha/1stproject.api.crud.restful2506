package com.example.mincheol1sr2.service;

import com.example.mincheol1sr2.dto.PostRequestDto;
import com.example.mincheol1sr2.dto.PostResponseDto;
import com.example.mincheol1sr2.entity.PostEntity;
import com.example.mincheol1sr2.repository.PostJpaRepository;
import com.example.mincheol1sr2.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto) {

        PostEntity post = PostEntity.builder()
                .title(postRequestDto.getTitle())
                .author(postRequestDto.getAuthor())
                .content(postRequestDto.getContent())
                .build();

        PostEntity saved = postJpaRepository.save(post);

        return PostResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .message("게시글 작성 성공!")
                .createAt(saved.getCreateAt())
                .build();

    }

    @Transactional
    public PostResponseDto updatePost(Integer id, PostRequestDto postRequestDto) {
        PostEntity post = postJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));

        // 값만 업데이트
        post.update(postRequestDto.getTitle(), postRequestDto.getContent());

        PostEntity saved = postJpaRepository.save(post);

        // 수정된 결과로 ResponseDto 만들어서 반환
        return PostResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .author(saved.getAuthor())
                .message("게시글 수정 성공")
                .createAt(saved.getCreateAt())
                .build();
    }

    @Transactional
    public void deletePost(Integer id) {
        postJpaRepository.deleteById(id);
    }
}
