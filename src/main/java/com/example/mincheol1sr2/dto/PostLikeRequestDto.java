package com.example.mincheol1sr2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostLikeRequestDto {

    private Integer userId;
    private Integer postId;

}
