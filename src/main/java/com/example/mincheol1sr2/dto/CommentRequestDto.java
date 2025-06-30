package com.example.mincheol1sr2.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDto {

    private Integer postId;
    private String content;
    private String author;
}
