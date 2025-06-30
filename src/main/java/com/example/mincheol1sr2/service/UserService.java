package com.example.mincheol1sr2.service;

import com.example.mincheol1sr2.dto.LoginRequestDto;
import com.example.mincheol1sr2.dto.LoginResponseDto;
import com.example.mincheol1sr2.dto.SignupRequestDto;
import com.example.mincheol1sr2.dto.SignupResponseDto;
import com.example.mincheol1sr2.entity.UserEntity;
import com.example.mincheol1sr2.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;

    @Transactional
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {

        // 1. 이메일 중복 검사
        if (userJpaRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // 2. 패스워드 등 정보로 UserEntity 생성 (보통 비밀번호 암호화 추가)
        UserEntity userEntity = UserEntity.builder()
                .email(signupRequestDto.getEmail())
                .password(signupRequestDto.getPassword()) // 보안상 암호화 추천!
                .build();

        // 3. 저장
        UserEntity saved = userJpaRepository.save(userEntity);

        // 4. 저장 결과로 ResponseDto 만들어 반환
        return SignupResponseDto.builder()
                .email(saved.getEmail())
                .message("회원가입 성공!")
                .build();
    }

    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto  loginRequestDto) {

        UserEntity user = userJpaRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("이메일또는 비밀번호가 다릅니다."));

        return LoginResponseDto.builder()
                .email(user.getEmail())
                .message("로그인 성공!")
                .build();


    }
}
