package com.majorproject.cvmaker.Repository;

import com.majorproject.cvmaker.Entity.LanguageFormEntity;
import com.majorproject.cvmaker.Entity.SkillsFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LanguageFormRepository extends JpaRepository<LanguageFormEntity, Long> {
    @Query("SELECT e FROM LanguageFormEntity e where e.user.id = :id order by e.profileId desc limit 1")
    LanguageFormEntity findRecentData(@Param("id") Long id);
}
