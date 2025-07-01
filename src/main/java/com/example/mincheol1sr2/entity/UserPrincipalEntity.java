package com.example.mincheol1sr2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user_principal")
public class UserPrincipalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_principal_id")
    private Integer userPrincipalId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userPrincipalEntity")
    private Collection<UserPrincipalRolesEntity> userPrincipalRoles;

}
