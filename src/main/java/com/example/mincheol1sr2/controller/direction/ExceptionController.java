package com.example.mincheol1sr2.controller.direction;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exceptions")
public class ExceptionController {

    @GetMapping("/entrypoint")
    public void entrypointException() {
        throw new AuthenticationCredentialsNotFoundException("강제 인증 예외 발생!");
    }
}
