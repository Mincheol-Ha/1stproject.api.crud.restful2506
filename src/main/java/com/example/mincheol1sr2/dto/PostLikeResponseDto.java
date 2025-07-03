package com.example.mincheol1sr2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostLikeResponseDto {

    private Integer userId;
    private Integer postId;
    private String message;
    private LocalDateTime createdAt;
    private Long count;


}
