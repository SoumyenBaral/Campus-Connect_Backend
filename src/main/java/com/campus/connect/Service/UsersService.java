package com.campus.connect.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.campus.connect.Dto.AdminCreateRequest;
import com.campus.connect.Dto.SuperAdminCreateRequest;
import com.campus.connect.Dto.UserUpdateRequest;
import com.campus.connect.Entity.Organisation;
import com.campus.connect.Entity.Users;

public interface UsersService {

    // Public Registration
    String saveUser(Users user);

    // Read all users
    List<Users> getAllUsers();

    // Delete all users
    String deleteAllUsers();

    // Retrieve user by email
    Optional<Users> findByEmail(String email);

    // Retrieve user by ID
    Optional<Users> findById(Long id);

    // Login logic - returns the user object if successful, null otherwise
    Users loginUser(String email, String password);

    // Forgot password
    void forgotPassword(String email);

    // Reset password
    void resetPassword(String token, String newPassword);

    // Admin Management (by Super Admin)
    List<Users> getAllAdmins();
    Users createAdmin(AdminCreateRequest request);
    Users updateAdmin(Long id, UserUpdateRequest request);
    void deleteAdmin(Long id);
    Users setAdminStatus(Long id, String status);

    // Super Admin Management (by Super Admin)
    List<Users> getAllSuperAdmins();
    Users createSuperAdmin(SuperAdminCreateRequest request);
    Users updateSuperAdmin(Long id, UserUpdateRequest request);
    Users setSuperAdminStatus(Long id, String status);

    // System Overview Stats
    Map<String, Object> getSystemStats();

    // Organisations
    List<Organisation> getAllOrganisations();
}