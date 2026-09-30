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

    @Value("${app.superadmin.dev1.name:Super Admin 1}")
    private String dev1Name;

    @Value("${app.superadmin.dev1.email:superadmin1@campusconnect.com}")
    private String dev1Email;

    @Value("${app.superadmin.dev1.password:Admin@Dev1!2026}")
    private String dev1Password;

    @Value("${app.superadmin.dev1.contact:9876543210}")
    private String dev1Contact;

    @Value("${app.superadmin.dev2.name:Super Admin 2}")
    private String dev2Name;

    @Value("${app.superadmin.dev2.email:superadmin2@campusconnect.com}")
    private String dev2Email;

    @Value("${app.superadmin.dev2.password:Admin@Dev2!2026}")
    private String dev2Password;

    @Value("${app.superadmin.dev2.contact:9876543211}")
    private String dev2Contact;

    @Override
    public void run(String... args) {
        initSuperAdmin(dev1Name, dev1Email, dev1Password, dev1Contact);
        initSuperAdmin(dev2Name, dev2Email, dev2Password, dev2Contact);
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
