package com.example.mincheol1sr2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentLikeResponseDto {

    private Integer userId;
    private Integer commentId;
    private Integer postId;
    private String message;
    private LocalDateTime createAt;
}
