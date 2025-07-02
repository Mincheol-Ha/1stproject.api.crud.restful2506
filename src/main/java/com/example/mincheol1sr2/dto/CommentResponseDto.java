package com.example.mincheol1sr2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDto {

    private Integer postId;
    private String content;
    private String author;
    private String message;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

}
