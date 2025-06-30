package com.example.mincheol1sr2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentLikeDeleteRequestDto {

    private Integer commentId;
    private Integer postId;

}
