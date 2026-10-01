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
import com.campus.connect.Dto.CoordinatorCreateRequest;
import com.campus.connect.Dto.HostCreateRequest;
import com.campus.connect.Dto.SuperAdminCreateRequest;
import com.campus.connect.Dto.UserUpdateRequest;
import com.campus.connect.Entity.Events;
import com.campus.connect.Entity.Organisation;
import com.campus.connect.Entity.Users;
import com.campus.connect.Service.EventsService;
import com.campus.connect.Service.UsersService;

@RestController
@RequestMapping("/api/super-admin")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class SuperAdminController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private EventsService eventsService;

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
    // HOST MANAGEMENT (SUPER ADMIN)
    // ==========================================

    @GetMapping("/hosts")
    public ResponseEntity<List<Users>> getAllHosts() {
        return ResponseEntity.ok(usersService.getAllHosts());
    }

    @GetMapping("/unapproved-hosts")
    public ResponseEntity<List<Users>> getUnapprovedHosts() {
        return ResponseEntity.ok(usersService.getUnapprovedHosts());
    }

    @PostMapping("/hosts")
    public ResponseEntity<?> createHost(@RequestBody HostCreateRequest request) {
        try {
            Users created = usersService.createHost(request);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error creating Host: " + e.getMessage()));
        }
    }

    @PutMapping("/hosts/{id}/approve")
    public ResponseEntity<?> approveHost(@PathVariable Long id, @RequestParam boolean approve) {
        try {
            Users updated = usersService.approveHost(id, approve);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PatchMapping("/hosts/{id}/status")
    public ResponseEntity<?> setHostStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Users updated = usersService.setHostStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/hosts/{id}")
    public ResponseEntity<?> deleteHost(@PathVariable Long id) {
        try {
            usersService.deleteHost(id);
            return ResponseEntity.ok(Map.of("message", "Host deleted successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ==========================================
    // COORDINATOR MANAGEMENT (SUPER ADMIN)
    // ==========================================

    @GetMapping("/coordinators")
    public ResponseEntity<List<Users>> getAllCoordinators() {
        return ResponseEntity.ok(usersService.getAllCoordinators());
    }

    @PostMapping("/coordinators")
    public ResponseEntity<?> createCoordinator(@RequestBody CoordinatorCreateRequest request) {
        try {
            Users created = usersService.createCoordinator(request);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error creating Coordinator: " + e.getMessage()));
        }
    }

    @PatchMapping("/coordinators/{id}/status")
    public ResponseEntity<?> setCoordinatorStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Users updated = usersService.setCoordinatorStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/coordinators/{id}")
    public ResponseEntity<?> deleteCoordinator(@PathVariable Long id) {
        try {
            usersService.deleteCoordinator(id);
            return ResponseEntity.ok(Map.of("message", "Coordinator deleted successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ==========================================
    // EVENT MANAGEMENT (SUPER ADMIN)
    // ==========================================

    @GetMapping("/events")
    public ResponseEntity<List<Events>> getAllEvents() {
        return ResponseEntity.ok(eventsService.getAllEvents());
    }

    @PostMapping("/events")
    public ResponseEntity<?> createEvent(@RequestBody Events event) {
        try {
            String result = eventsService.CreateEvent(event);
            if (result.startsWith("Error:")) {
                return ResponseEntity.badRequest().body(Map.of("error", result));
            }
            return new ResponseEntity<>(Map.of("message", result), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to create event: " + e.getMessage()));
        }
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        try {
            eventsService.deleteEvent(id);
            return ResponseEntity.ok(Map.of("message", "Event deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to delete event: " + e.getMessage()));
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
