package com.campus.connect.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.connect.Dto.AdminCreateRequest;
import com.campus.connect.Dto.CoordinatorCreateRequest;
import com.campus.connect.Dto.HostCreateRequest;
import com.campus.connect.Dto.SuperAdminCreateRequest;
import com.campus.connect.Dto.UserUpdateRequest;
import com.campus.connect.Entity.Organisation;
import com.campus.connect.Entity.Users;
import com.campus.connect.Entity.Enum.Role;
import com.campus.connect.Repository.EventsRepository;
import com.campus.connect.Repository.OrganisationRepository;
import com.campus.connect.Repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Pattern CONTACT_PATTERN = Pattern.compile("^\\d{10}$");

    @Override
    public String saveUser(Users user) {
        // Validate contact format
        if (user.getContact() == null || !CONTACT_PATTERN.matcher(user.getContact()).matches()) {
            throw new IllegalArgumentException("Contact number must be exactly 10 digits (numbers only).");
        }

        // Section 7 & 8: Super Admin can NEVER be registered via public signup
        if (user.getRole() == Role.SUPER_ADMIN) {
            throw new IllegalArgumentException("Super Admin accounts cannot be created through public signup.");
        }

        // Section 8 & 12: Admin signup requires Organisation
        if (user.getRole() == Role.ADMIN) {
            if (user.getOrganisation() == null || user.getOrganisation().getOrganisationName() == null
                    || user.getOrganisation().getOrganisationName().trim().isEmpty()) {
                throw new IllegalArgumentException("Organisation name is required for Admin registration.");
            }
            String orgName = user.getOrganisation().getOrganisationName().trim();
            Organisation org = organisationRepository.findByOrganisationNameIgnoreCase(orgName)
                    .orElseGet(() -> organisationRepository.save(new Organisation(null, orgName, "ACTIVE")));
            user.setOrganisation(org);
        } else {
            // Section 9, 10, 11, 12: Coordinator, Host, and User must NOT have an organisation
            user.setOrganisation(null);
        }

        // Ensure email uniqueness
        String normalizedEmail = user.getEmail().trim().toLowerCase();
        if (usersRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("An account with email " + normalizedEmail + " already exists.");
        }
        user.setEmail(normalizedEmail);

        // Password encryption
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getStatus() == null || user.getStatus().isEmpty()) {
            user.setStatus("ACTIVE");
        }

        if (user.getRole() == Role.HOST) {
            user.setApproved(false);
        } else {
            user.setApproved(true);
        }

        user.setCreatedAt(LocalDateTime.now());
        usersRepository.save(user);
        return "created success";
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return usersRepository.findByEmail(email.trim().toLowerCase());
    }

    @Override
    public Optional<Users> findById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public Users loginUser(String email, String password) {
        Optional<Users> userOptional = usersRepository.findByEmail(email.trim().toLowerCase());

        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            boolean matches = passwordEncoder.matches(password, user.getPassword());
            // Graceful migration for existing plain text accounts
            if (!matches && user.getPassword().equals(password)) {
                user.setPassword(passwordEncoder.encode(password));
                usersRepository.save(user);
                matches = true;
            }

            if (matches) {
                if ("INACTIVE".equalsIgnoreCase(user.getStatus())) {
                    throw new IllegalStateException("Your account is currently deactivated. Please contact an administrator.");
                }
                return user;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public String deleteAllUsers() {
        usersRepository.deleteAll();
        return "All users deleted successfully";
    }

    @Override
    public void forgotPassword(String email) {
        // Handled as in existing codebase
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        // Handled as in existing codebase
    }

    // ==========================================
    // SUPER ADMIN - ADMIN MANAGEMENT
    // ==========================================

    @Override
    public List<Users> getAllAdmins() {
        return usersRepository.findByRoleOrderByCreatedAtDesc(Role.ADMIN);
    }

    @Override
    public Users createAdmin(AdminCreateRequest request) {
        if (request.getContact() == null || !CONTACT_PATTERN.matcher(request.getContact()).matches()) {
            throw new IllegalArgumentException("Contact number must be exactly 10 digits.");
        }

        String normalizedEmail = request.getEmail().trim().toLowerCase();
        if (usersRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("An account with email " + normalizedEmail + " already exists.");
        }

        if (request.getOrganisationName() == null || request.getOrganisationName().trim().isEmpty()) {
            throw new IllegalArgumentException("Organisation is required for Admin creation.");
        }

        String orgName = request.getOrganisationName().trim();
        Organisation org = organisationRepository.findByOrganisationNameIgnoreCase(orgName)
                .orElseGet(() -> organisationRepository.save(new Organisation(null, orgName, "ACTIVE")));

        Users admin = new Users();
        admin.setName(request.getName());
        admin.setEmail(normalizedEmail);
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setContact(request.getContact());
        admin.setRole(Role.ADMIN);
        admin.setOrganisation(org);
        admin.setStatus("ACTIVE");
        admin.setApproved(true);
        admin.setCreatedAt(LocalDateTime.now());

        return usersRepository.save(admin);
    }

    @Override
    public Users updateAdmin(Long id, UserUpdateRequest request) {
        Users admin = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin with ID " + id + " not found."));

        if (admin.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("Target user is not an Admin.");
        }

        if (request.getName() != null && !request.getName().trim().isEmpty()) {
            admin.setName(request.getName().trim());
        }

        if (request.getContact() != null && !request.getContact().trim().isEmpty()) {
            if (!CONTACT_PATTERN.matcher(request.getContact()).matches()) {
                throw new IllegalArgumentException("Contact number must be exactly 10 digits.");
            }
            admin.setContact(request.getContact().trim());
        }

        if (request.getOrganisationName() != null && !request.getOrganisationName().trim().isEmpty()) {
            String orgName = request.getOrganisationName().trim();
            Organisation org = organisationRepository.findByOrganisationNameIgnoreCase(orgName)
                    .orElseGet(() -> organisationRepository.save(new Organisation(null, orgName, "ACTIVE")));
            admin.setOrganisation(org);
        }

        if (request.getStatus() != null && !request.getStatus().trim().isEmpty()) {
            admin.setStatus(request.getStatus().trim().toUpperCase());
        }

        return usersRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        Users admin = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin with ID " + id + " not found."));

        if (admin.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("Only Admin accounts can be deleted through this endpoint.");
        }
        usersRepository.delete(admin);
    }

    @Override
    public Users setAdminStatus(Long id, String status) {
        Users admin = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin with ID " + id + " not found."));

        if (admin.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("User is not an Admin.");
        }

        admin.setStatus(status.toUpperCase());
        return usersRepository.save(admin);
    }

    // ==========================================
    // SUPER ADMIN - SUPER ADMIN MANAGEMENT
    // ==========================================

    @Override
    public List<Users> getAllSuperAdmins() {
        return usersRepository.findByRoleOrderByCreatedAtDesc(Role.SUPER_ADMIN);
    }

    @Override
    public Users createSuperAdmin(SuperAdminCreateRequest request) {
        if (usersRepository.countByRole(Role.SUPER_ADMIN) >= 1) {
            throw new IllegalArgumentException("Security Policy: Only one Super Admin account is permitted in the system.");
        }

        if (request.getContact() == null || !CONTACT_PATTERN.matcher(request.getContact()).matches()) {
            throw new IllegalArgumentException("Contact number must be exactly 10 digits.");
        }

        String normalizedEmail = request.getEmail().trim().toLowerCase();
        if (usersRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("An account with email " + normalizedEmail + " already exists.");
        }

        Users superAdmin = new Users();
        superAdmin.setName(request.getName());
        superAdmin.setEmail(normalizedEmail);
        superAdmin.setPassword(passwordEncoder.encode(request.getPassword()));
        superAdmin.setContact(request.getContact());
        superAdmin.setRole(Role.SUPER_ADMIN);
        superAdmin.setOrganisation(null); // Super Admin has no organisation
        superAdmin.setStatus("ACTIVE");
        superAdmin.setApproved(true);
        superAdmin.setCreatedAt(LocalDateTime.now());

        return usersRepository.save(superAdmin);
    }

    @Override
    public Users updateSuperAdmin(Long id, UserUpdateRequest request) {
        Users superAdmin = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Super Admin with ID " + id + " not found."));

        if (superAdmin.getRole() != Role.SUPER_ADMIN) {
            throw new IllegalArgumentException("Target user is not a Super Admin.");
        }

        if (request.getName() != null && !request.getName().trim().isEmpty()) {
            superAdmin.setName(request.getName().trim());
        }

        if (request.getContact() != null && !request.getContact().trim().isEmpty()) {
            if (!CONTACT_PATTERN.matcher(request.getContact()).matches()) {
                throw new IllegalArgumentException("Contact number must be exactly 10 digits.");
            }
            superAdmin.setContact(request.getContact().trim());
        }

        if (request.getStatus() != null && !request.getStatus().trim().isEmpty()) {
            if ("INACTIVE".equalsIgnoreCase(request.getStatus().trim())) {
                throw new IllegalArgumentException("Security Policy: The single Super Admin account cannot be deactivated.");
            }
            superAdmin.setStatus(request.getStatus().trim().toUpperCase());
        }

        return usersRepository.save(superAdmin);
    }

    @Override
    public Users setSuperAdminStatus(Long id, String status) {
        Users superAdmin = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Super Admin with ID " + id + " not found."));

        if (superAdmin.getRole() != Role.SUPER_ADMIN) {
            throw new IllegalArgumentException("User is not a Super Admin.");
        }

        if ("INACTIVE".equalsIgnoreCase(status)) {
            throw new IllegalArgumentException("Security Policy: The single Super Admin account cannot be deactivated.");
        }

        superAdmin.setStatus(status.toUpperCase());
        return usersRepository.save(superAdmin);
    }

    // ==========================================
    // HOST MANAGEMENT (SUPER ADMIN)
    // ==========================================

    @Override
    public List<Users> getAllHosts() {
        return usersRepository.findByRoleOrderByCreatedAtDesc(Role.HOST);
    }

    @Override
    public List<Users> getUnapprovedHosts() {
        return usersRepository.findByRoleAndIsApproved(Role.HOST, false);
    }

    @Override
    public Users approveHost(Long id, boolean approve) {
        Users host = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Host with ID " + id + " not found."));
        if (host.getRole() != Role.HOST) {
            throw new IllegalArgumentException("User is not a Host.");
        }
        host.setApproved(approve);
        return usersRepository.save(host);
    }

    @Override
    public Users createHost(HostCreateRequest request) {
        if (request.getContact() == null || !CONTACT_PATTERN.matcher(request.getContact()).matches()) {
            throw new IllegalArgumentException("Contact number must be exactly 10 digits.");
        }

        String normalizedEmail = request.getEmail().trim().toLowerCase();
        if (usersRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("An account with email " + normalizedEmail + " already exists.");
        }

        Users host = new Users();
        host.setName(request.getName().trim());
        host.setEmail(normalizedEmail);
        host.setPassword(passwordEncoder.encode(request.getPassword()));
        host.setContact(request.getContact().trim());
        host.setRole(Role.HOST);
        host.setOrganisation(null);
        host.setStatus("ACTIVE");
        host.setApproved(true);
        host.setCreatedAt(LocalDateTime.now());

        return usersRepository.save(host);
    }

    @Override
    public Users setHostStatus(Long id, String status) {
        Users host = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Host with ID " + id + " not found."));
        if (host.getRole() != Role.HOST) {
            throw new IllegalArgumentException("User is not a Host.");
        }
        host.setStatus(status.toUpperCase());
        return usersRepository.save(host);
    }

    @Override
    public void deleteHost(Long id) {
        Users host = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Host with ID " + id + " not found."));
        if (host.getRole() != Role.HOST) {
            throw new IllegalArgumentException("User is not a Host.");
        }
        usersRepository.delete(host);
    }

    // ==========================================
    // COORDINATOR MANAGEMENT (SUPER ADMIN)
    // ==========================================

    @Override
    public List<Users> getAllCoordinators() {
        return usersRepository.findByRoleOrderByCreatedAtDesc(Role.COORDINATOR);
    }

    @Override
    public Users createCoordinator(CoordinatorCreateRequest request) {
        if (request.getContact() == null || !CONTACT_PATTERN.matcher(request.getContact()).matches()) {
            throw new IllegalArgumentException("Contact number must be exactly 10 digits.");
        }

        String normalizedEmail = request.getEmail().trim().toLowerCase();
        if (usersRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("An account with email " + normalizedEmail + " already exists.");
        }

        Users coordinator = new Users();
        coordinator.setName(request.getName().trim());
        coordinator.setEmail(normalizedEmail);
        coordinator.setPassword(passwordEncoder.encode(request.getPassword()));
        coordinator.setContact(request.getContact().trim());
        coordinator.setRole(Role.COORDINATOR);
        coordinator.setOrganisation(null);
        coordinator.setStatus("ACTIVE");
        coordinator.setApproved(true);
        coordinator.setCreatedAt(LocalDateTime.now());

        return usersRepository.save(coordinator);
    }

    @Override
    public Users setCoordinatorStatus(Long id, String status) {
        Users coordinator = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Coordinator with ID " + id + " not found."));
        if (coordinator.getRole() != Role.COORDINATOR) {
            throw new IllegalArgumentException("User is not a Coordinator.");
        }
        coordinator.setStatus(status.toUpperCase());
        return usersRepository.save(coordinator);
    }

    @Override
    public void deleteCoordinator(Long id) {
        Users coordinator = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Coordinator with ID " + id + " not found."));
        if (coordinator.getRole() != Role.COORDINATOR) {
            throw new IllegalArgumentException("User is not a Coordinator.");
        }
        usersRepository.delete(coordinator);
    }

    // ==========================================
    // SYSTEM OVERVIEW STATS & ORGANISATIONS
    // ==========================================

    @Override
    public Map<String, Object> getSystemStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalSuperAdmins", usersRepository.countByRole(Role.SUPER_ADMIN));
        stats.put("totalAdmins", usersRepository.countByRole(Role.ADMIN));
        stats.put("totalCoordinators", usersRepository.countByRole(Role.COORDINATOR));
        stats.put("totalHosts", usersRepository.countByRole(Role.HOST));
        stats.put("totalUsers", usersRepository.countByRole(Role.USER));
        stats.put("totalEvents", eventsRepository.count());
        stats.put("totalOrganisations", organisationRepository.count());
        return stats;
    }

    @Override
    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }
}