package com.example.mincheol1sr2.config;

import com.example.mincheol1sr2.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class JwtProvider {
    private final String secretKey = "SuperCodingFirstProjectSecretKey!!!!!##@@";
    public String generateToken(UserEntity  user) {
        // JWT 빌드 및 signWith
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();

    }

}
