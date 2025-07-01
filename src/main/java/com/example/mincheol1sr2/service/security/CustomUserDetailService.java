package com.example.mincheol1sr2.service.security;

import com.example.mincheol1sr2.entity.RolesEntity;
import com.example.mincheol1sr2.entity.UserPrincipalEntity;
import com.example.mincheol1sr2.entity.UserPrincipalRolesEntity;
import com.example.mincheol1sr2.repository.UserJpaRepository;
import com.example.mincheol1sr2.userDetail.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.util.stream.Collectors;

@Primary
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserPrincipalEntity userPrincipalEntity = userJpaRepository.findByEmailFetchJoin(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("email에 해당하는 UserPrincipal가 없습니다.")
                );

        CustomUserDetails customUserDetails = CustomUserDetails.builder()
                .userId(userPrincipalEntity.getUserPrincipalId())
                .email(userPrincipalEntity.getEmail())
                .password(userPrincipalEntity.getPassword())
                .authorities(
                        userPrincipalEntity.getUserPrincipalRoles().stream()
                                .map(UserPrincipalRolesEntity::getRoles)
                                .map(RolesEntity::getEmail)
                                .collect(Collectors.toList())
                )
                .build();

        return customUserDetails;
    }

}
