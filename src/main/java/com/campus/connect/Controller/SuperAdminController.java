package com.campus.connect.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Dto.AdminCreateRequest;
import com.campus.connect.Dto.SuperAdminCreateRequest;
import com.campus.connect.Dto.UserUpdateRequest;
import com.campus.connect.Entity.Organisation;
import com.campus.connect.Entity.Users;
import com.campus.connect.Service.UsersService;

@RestController
@RequestMapping("/api/super-admin")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class SuperAdminController {

    @Autowired
    private UsersService usersService;

    // ==========================================
    // ADMIN MANAGEMENT
    // ==========================================

    @GetMapping("/admins")
    public ResponseEntity<List<Users>> getAllAdmins() {
        return ResponseEntity.ok(usersService.getAllAdmins());
    }

    @PostMapping("/admins")
    public ResponseEntity<?> createAdmin(@RequestBody AdminCreateRequest request) {
        try {
            Users created = usersService.createAdmin(request);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error creating Admin: " + e.getMessage()));
        }
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        return usersService.findById(id)
                .map(user -> ResponseEntity.ok((Object) user))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Admin not found.")));
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        try {
            Users updated = usersService.updateAdmin(id, request);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to update admin: " + e.getMessage()));
        }
    }

    @PatchMapping("/admins/{id}/status")
    public ResponseEntity<?> setAdminStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Users updated = usersService.setAdminStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        try {
            usersService.deleteAdmin(id);
            return ResponseEntity.ok(Map.of("message", "Admin deleted successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ==========================================
    // SUPER ADMIN MANAGEMENT
    // ==========================================

    @GetMapping("/super-admins")
    public ResponseEntity<List<Users>> getAllSuperAdmins() {
        return ResponseEntity.ok(usersService.getAllSuperAdmins());
    }

    @PostMapping("/super-admins")
    public ResponseEntity<?> createSuperAdmin(@RequestBody SuperAdminCreateRequest request) {
        try {
            Users created = usersService.createSuperAdmin(request);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error creating Super Admin: " + e.getMessage()));
        }
    }

    @GetMapping("/super-admins/{id}")
    public ResponseEntity<?> getSuperAdminById(@PathVariable Long id) {
        return usersService.findById(id)
                .map(user -> ResponseEntity.ok((Object) user))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Super Admin not found.")));
    }

    @PutMapping("/super-admins/{id}")
    public ResponseEntity<?> updateSuperAdmin(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        try {
            Users updated = usersService.updateSuperAdmin(id, request);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PatchMapping("/super-admins/{id}/status")
    public ResponseEntity<?> setSuperAdminStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Users updated = usersService.setSuperAdminStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ==========================================
    // SYSTEM OVERVIEW STATS & ORGANISATIONS
    // ==========================================

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getSystemStats() {
        return ResponseEntity.ok(usersService.getSystemStats());
    }

    @GetMapping("/organisations")
    public ResponseEntity<List<Organisation>> getAllOrganisations() {
        return ResponseEntity.ok(usersService.getAllOrganisations());
    }
}
