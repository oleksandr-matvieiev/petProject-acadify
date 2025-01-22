package org.example.acadify;

import org.example.acadify.Enums.RoleName;
import org.example.acadify.exceptions.RoleNotFoundExc;
import org.example.acadify.model.Role;
import org.example.acadify.model.User;
import org.example.acadify.repository.RoleRepository;
import org.example.acadify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.superadmin.firstName}")
    private String adminFirstName;
    @Value("${app.superadmin.lastName}")
    private String adminLastName;
    @Value("${app.superadmin.email}")
    private String adminEmail;
    @Value("${app.superadmin.password}")
    private String adminPassword;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
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
        if (!userRepository.existsByEmail(adminEmail)) {
            User adminUser = new User();

            adminUser.setFirstName(adminFirstName);
            adminUser.setLastName(adminLastName);
            adminUser.setEmail(adminEmail);
            adminUser.setPassword(passwordEncoder.encode(adminPassword));

            Role adminRole = roleRepository.findByRoleName(RoleName.ADMIN)
                    .orElseThrow(RoleNotFoundExc::new);
            adminUser.setRoles(List.of(adminRole));

            userRepository.save(adminUser);
        }
    }
}
