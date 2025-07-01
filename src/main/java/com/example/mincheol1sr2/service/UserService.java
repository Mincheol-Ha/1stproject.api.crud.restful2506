package com.example.mincheol1sr2.service;

import com.example.mincheol1sr2.config.JwtProvider;
import com.example.mincheol1sr2.dto.LoginRequestDto;
import com.example.mincheol1sr2.dto.LoginResponseDto;
import com.example.mincheol1sr2.dto.SignupRequestDto;
import com.example.mincheol1sr2.dto.SignupResponseDto;
import com.example.mincheol1sr2.entity.RolesEntity;
import com.example.mincheol1sr2.entity.UserEntity;
import com.example.mincheol1sr2.entity.UserPrincipalEntity;
import com.example.mincheol1sr2.entity.UserPrincipalRolesEntity;
import com.example.mincheol1sr2.repository.RolesRepository;
import com.example.mincheol1sr2.repository.UserJpaRepository;
import com.example.mincheol1sr2.repository.UserPrincipalRepository;
import com.example.mincheol1sr2.repository.UserPrincipalRolesRepository;
import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final JwtProvider jwtProvider;

    private final UserPrincipalRepository userPrincipalRepository;
    private final RolesRepository rolesRepository;
    private final UserPrincipalRolesRepository userPrincipalRolesRepository;

    private PasswordEncoder passwordEncoder;

    public boolean signUp(SignupRequestDto singUpRequestDto) {
        String email =  singUpRequestDto.getEmail();
        String password = singUpRequestDto.getPassword();

        if (userPrincipalRepository.existsByEmail(email)) {
            return false;
        }
        // 2. 유저가 있으면 ID만 등록 아니면 유저 만들기
        UserEntity userFound = userJpaRepository.findUserByEmail(email)
                .orElseGet(() -> userJpaRepository.save(UserEntity.builder()
                                                                    .email(email)
                                                                    .build()));
        // 3. Password 등록 기본 ROLE_USER

        RolesEntity roles = rolesRepository.findByEmail("ROLE_USER")
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        UserPrincipalEntity userPrincipalEntity = UserPrincipalEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        userPrincipalRepository.save(userPrincipalEntity);
        userPrincipalRolesRepository.save(
                UserPrincipalRolesEntity.builder()
                        .roles(roles)
                        .userPrincipalEntity(userPrincipalEntity)
                        .build()

        );

        return true;

    }

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

        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new IllegalArgumentException("이메일또는 비밀번호가 다릅니다.");
        }

        String accessToken = jwtProvider.generateToken(user);


        return LoginResponseDto.builder()
                .email(user.getEmail())
                .message("로그인 성공!")
                .accessToken(accessToken)
                .build();

    }
}
