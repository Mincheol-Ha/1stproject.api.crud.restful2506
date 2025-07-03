package com.example.mincheol1sr2.dto;

import com.example.mincheol1sr2.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {

    private String title;
    private String content;
    private String author;
    private String message;
    private LocalDateTime createAt;

    public static PostResponseDto fromEntity(PostEntity postEntity) {
        return PostResponseDto.builder()
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .author(postEntity.getAuthor())
                .createAt(postEntity.getCreateAt())
                .build();

    }
}
