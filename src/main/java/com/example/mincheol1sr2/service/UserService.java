package com.example.mincheol1sr2.service;

import com.example.mincheol1sr2.config.JwtProvider;
import com.example.mincheol1sr2.config.security.JwtTokenProvider;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;
   // private final JwtProvider jwtProvider;
    private final JwtTokenProvider  jwtTokenProvider;

    private final UserPrincipalRepository userPrincipalRepository;
    private final RolesRepository rolesRepository;
    private final UserPrincipalRolesRepository userPrincipalRolesRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Transactional
    public SignupResponseDto signup(SignupRequestDto signUpRequestDto) {
        String email = signUpRequestDto.getEmail();
        String password = signUpRequestDto.getPassword();

        if (userPrincipalRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // 유저 정보가 없으면 생성
        UserEntity user = userJpaRepository.findUserByEmail(email)
                .orElseGet(() -> userJpaRepository.save(UserEntity.builder()
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .build()));

        // 기본 권한(ROLE_USER) 엔티티 조회
        RolesEntity role = rolesRepository.findByEmail("ROLE_USER")
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        // 인증용 유저 정보 저장 (비밀번호 암호화)
        UserPrincipalEntity userPrincipal = UserPrincipalEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .user(user) // 필요하다면 UserEntity와 연관관계 매핑
                .build();
        userPrincipalRepository.save(userPrincipal);

        // 유저와 권한 연관 엔티티 저장
        userPrincipalRolesRepository.save(
                UserPrincipalRolesEntity.builder()
                        .roles(role)
                        .userPrincipalEntity(userPrincipal)
                        .build()
        );

        // DTO 반환
        return SignupResponseDto.builder()
                .email(email)
                .message("회원가입 성공!")
                .role(role.getEmail())
                .build();
    }




    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        UserEntity user = userJpaRepository.findUserByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일또는 비밀번호가 다릅니다."));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이메일또는 비밀번호가 다릅니다.");
        }

        // 권한(roles) 리스트 추출
        UserPrincipalEntity userPrincipal = userPrincipalRepository.findByEmailFetchJoin(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<String> roles = userPrincipal.getUserPrincipalRoles().stream()
                .map(r -> r.getRoles().getEmail()) // "ROLE_USER"
                .collect(Collectors.toList());

        String accessToken = jwtTokenProvider.createToken(user.getEmail(), roles);

        return LoginResponseDto.builder()
                .email(user.getEmail())
                .message("로그인 성공!")
                .accessToken(accessToken)
                .build();
    }

}
