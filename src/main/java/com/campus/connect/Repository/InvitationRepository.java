package com.campus.connect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campus.connect.Entity.Invitations;

public interface InvitationRepository extends JpaRepository<Invitations, Long> {

}
