package com.example.mincheol1sr2.repository;

import com.example.mincheol1sr2.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends
        JpaRepository<RolesEntity, Integer> {

    Optional<RolesEntity> findByEmail(String email);
}

