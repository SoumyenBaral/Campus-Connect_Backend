package com.campus.connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campus.connect.Entity.Host;
import com.campus.connect.Entity.Users;

public interface HostRepository extends JpaRepository<Host, Integer>{
List<Host> findByApprovalStatus(String status);
    
    /**
     * Finds the Host application associated with a specific user.
     * Used to check if a user has a pending application for approval/rejection.
     * @param user The Users entity.
     * @return The Host entity for that user, or null if not found.
     */
    Host findByUser(Users user);
}
