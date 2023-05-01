package com.majorproject.cvmaker.Repository;

import com.majorproject.cvmaker.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmailIgnoreCase(String email);

    UserEntity findByEmail(String email);
}
