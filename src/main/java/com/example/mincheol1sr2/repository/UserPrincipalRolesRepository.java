package com.example.mincheol1sr2.repository;

import com.example.mincheol1sr2.entity.UserPrincipalRolesEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface  UserPrincipalRolesRepository extends JpaRepository<UserPrincipalRolesEntity, Integer> {
}
