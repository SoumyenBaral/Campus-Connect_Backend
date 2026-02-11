package com.campus.connect.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.campus.connect.Entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long>
{

	
	  Optional<Otp> findTopByEmailOrderByIdDesc(String email);

	    @Modifying
	    @Query("UPDATE Otp o SET o.isUsed = true WHERE o.email = :email")
	    void invalidateOtps(@Param("email") String email);
	
}