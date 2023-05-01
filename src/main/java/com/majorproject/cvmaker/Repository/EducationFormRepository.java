package com.majorproject.cvmaker.Repository;

import com.majorproject.cvmaker.Entity.EducationFormEntity;
import com.majorproject.cvmaker.Entity.ProfileFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EducationFormRepository extends JpaRepository<EducationFormEntity, Long> {
    @Query("SELECT e FROM EducationFormEntity e where e.user.id = :id order by e.profileId desc limit 1")
    EducationFormEntity findRecentData(@Param("id") Long id);

}
