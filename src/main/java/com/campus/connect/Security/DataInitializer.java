package com.campus.connect.Security;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.campus.connect.Entity.Users;
import com.campus.connect.Entity.Enum.Role;
import com.campus.connect.Repository.UsersRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${app.superadmin.name:Soumyen Baral}")
    private String superAdminName;

    @Value("${app.superadmin.email:soumyenbaral620@gmail.com}")
    private String superAdminEmail;

    @Value("${app.superadmin.password:Admin@2026!}")
    private String superAdminPassword;

    @Value("${app.superadmin.contact:9876543210}")
    private String superAdminContact;

    @Override
    public void run(String... args) {
        long superAdminCount = usersRepository.countByRole(Role.SUPER_ADMIN);
        if (superAdminCount == 0) {
            initSuperAdmin(superAdminName, superAdminEmail, superAdminPassword, superAdminContact);
        } else {
            logger.info("Security Policy: Single Super Admin policy active. Existing Super Admin count: {}. No additional Super Admin initialized.", superAdminCount);
        }
    }

    private void initSuperAdmin(String name, String email, String rawPassword, String contact) {
        if (usersRepository.findByEmail(email).isEmpty()) {
            Users superAdmin = new Users();
            superAdmin.setName(name);
            superAdmin.setEmail(email.trim().toLowerCase());
            superAdmin.setPassword(passwordEncoder.encode(rawPassword));
            superAdmin.setRole(Role.SUPER_ADMIN);
            superAdmin.setContact(contact);
            superAdmin.setStatus("ACTIVE");
            superAdmin.setApproved(true);
            superAdmin.setCreatedAt(LocalDateTime.now());
            superAdmin.setOrganisation(null); // Super Admins belong to system, not specific organization

            usersRepository.save(superAdmin);
            logger.info("Initialized initial Super Admin account: {}", email);
        }
    }
}
