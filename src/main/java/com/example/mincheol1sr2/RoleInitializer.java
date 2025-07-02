package com.example.mincheol1sr2;

import com.example.mincheol1sr2.entity.RolesEntity;
import com.example.mincheol1sr2.repository.RolesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {
    private final RolesRepository rolesRepository;

    public RoleInitializer(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public void run(String... args) {
        if (rolesRepository.findByEmail("ROLE_USER").isEmpty()) {
            rolesRepository.save(new RolesEntity(null, "ROLE_USER"));
        }
        if (rolesRepository.findByEmail("ROLE_ADMIN").isEmpty()) {
            rolesRepository.save(new RolesEntity(null, "ROLE_ADMIN"));
        }
    }
}