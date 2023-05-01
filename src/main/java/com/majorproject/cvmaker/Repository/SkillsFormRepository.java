package com.majorproject.cvmaker.Repository;

import com.majorproject.cvmaker.Entity.EducationFormEntity;
import com.majorproject.cvmaker.Entity.LanguageFormEntity;
import com.majorproject.cvmaker.Entity.SkillsFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SkillsFormRepository extends JpaRepository<SkillsFormEntity, Long> {
    @Query("SELECT e FROM SkillsFormEntity e where e.user.id = :id order by e.profileId desc limit 1")
    SkillsFormEntity findRecentData(@Param("id") Long id);
}
