package com.example.mincheol1sr2.repository;

import com.example.mincheol1sr2.dto.PostLikeRequestDto;
import com.example.mincheol1sr2.entity.UserEntity;
import com.example.mincheol1sr2.entity.UserPrincipalEntity;
import com.sun.security.auth.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, PostLikeRequestDto> {

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findById(Integer userId);

    @Query("SELECT up FROM UserPrincipalEntity up JOIN FETCH up.userPrincipalRoles upr JOIN FETCH upr.roles WHERE up.email = :email ")
    Optional<UserPrincipalEntity> findByEmailFetchJoin(String email);

    Optional<UserEntity> findUserByEmail(String email);

    Optional<UserEntity> findByEmail(String currentUserEmail);
}
