package org.example.acadify;

import org.example.acadify.Enums.RoleName;
import org.example.acadify.model.Role;
import org.example.acadify.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByRoleName(RoleName.TEACHER).isEmpty()) {
            Role teacherRole = new Role();
            teacherRole.setRoleName(RoleName.TEACHER);
            roleRepository.save(teacherRole);
        }
        if (roleRepository.findByRoleName(RoleName.STUDENT).isEmpty()) {
            Role studentRole = new Role();
            studentRole.setRoleName(RoleName.STUDENT);
            roleRepository.save(studentRole);
        }
        if (roleRepository.findByRoleName(RoleName.ADMIN).isEmpty()) {
            Role adminRole = new Role();
            adminRole.setRoleName(RoleName.ADMIN);
            roleRepository.save(adminRole);
        }
    }
}
