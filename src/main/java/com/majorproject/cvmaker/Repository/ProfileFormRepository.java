package com.majorproject.cvmaker.Repository;

import com.majorproject.cvmaker.Entity.ProfileFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfileFormRepository extends JpaRepository<ProfileFormEntity, Long> {
}
