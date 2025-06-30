package com.example.mincheol1sr2.repository;

import com.example.mincheol1sr2.dto.PostLikeRequestDto;
import com.example.mincheol1sr2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, PostLikeRequestDto> {

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findById(Integer userId);
}
