package com.example.mincheol1sr2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentLikeRequestDto {

    private Integer userId;
    private Integer commentId;
    private Integer postId;
}
