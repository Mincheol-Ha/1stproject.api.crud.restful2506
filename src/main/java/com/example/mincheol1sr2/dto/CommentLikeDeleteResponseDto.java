package com.example.mincheol1sr2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentLikeDeleteResponseDto {

    private Integer commentId;
    private Integer postId;
    private String message;
}
