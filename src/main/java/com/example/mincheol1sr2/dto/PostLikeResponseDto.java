package com.example.mincheol1sr2.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikeResponseDto {

    private Integer userId;
    private Integer postId;
    private String message;
    private LocalDateTime createdAt;


}
