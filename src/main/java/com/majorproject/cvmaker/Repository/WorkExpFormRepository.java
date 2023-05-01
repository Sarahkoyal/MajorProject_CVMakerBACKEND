package com.majorproject.cvmaker.Repository;

import com.majorproject.cvmaker.Entity.SkillsFormEntity;
import com.majorproject.cvmaker.Entity.WorkExpFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkExpFormRepository extends JpaRepository<WorkExpFormEntity, Long> {
    @Query("SELECT e FROM WorkExpFormEntity e where e.user.id = :id order by e.profileId desc limit 1")
    WorkExpFormEntity findRecentData(@Param("id") Long id);
}
