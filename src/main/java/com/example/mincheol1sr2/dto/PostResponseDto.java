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
public class PostResponseDto {

    private String title;
    private String content;
    private String author;
    private String message;
    private LocalDateTime createAt;
}
