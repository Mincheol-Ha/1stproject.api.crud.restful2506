package com.example.mincheol1sr2.repository;

import com.example.mincheol1sr2.entity.UserPrincipalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPrincipalRepository extends JpaRepository<UserPrincipalEntity, Integer> {

    @Query("SELECT up FROM UserPrincipalEntity up JOIN FETCH up.userPrincipalRoles upr JOIN FETCH upr.roles WHERE up.email = :email ")
    Optional<UserPrincipalEntity> findByEmailFetchJoin(String email);

    boolean existsByEmail(String email);
}
