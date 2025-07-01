package com.example.mincheol1sr2.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {

    private Integer postId;
    private String content;
    private String author;
    private LocalDateTime createdAt;

}
