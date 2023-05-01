package com.majorproject.cvmaker.Repository;

import com.majorproject.cvmaker.Entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OtpJpaRepository extends JpaRepository<OtpEntity, Long> {
    List<OtpEntity> findByEmail(String email);

    OtpEntity findByEmailAndOtp(String email, String otp);
}
