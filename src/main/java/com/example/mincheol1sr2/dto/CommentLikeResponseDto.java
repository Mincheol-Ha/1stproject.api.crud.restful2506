package com.example.mincheol1sr2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentLikeResponseDto {

    private Integer userId;
    private Integer commentId;
    private Integer postId;
    private LocalDateTime createAt;
}
