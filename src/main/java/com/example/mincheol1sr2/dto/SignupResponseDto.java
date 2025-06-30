package com.example.mincheol1sr2.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupResponseDto {

    private String email;
    private String message;

}
