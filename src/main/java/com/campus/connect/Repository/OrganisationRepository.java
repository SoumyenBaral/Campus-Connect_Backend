package com.campus.connect.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campus.connect.Entity.Organisation;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
    Optional<Organisation> findByOrganisationName(String organisationName);
    Optional<Organisation> findByOrganisationNameIgnoreCase(String organisationName);
    boolean existsByOrganisationNameIgnoreCase(String organisationName);
}
