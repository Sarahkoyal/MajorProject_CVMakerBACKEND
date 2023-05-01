package com.majorproject.cvmaker.Repository;

import com.majorproject.cvmaker.Entity.CVEntity;
import com.majorproject.cvmaker.Entity.SkillsFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CVRepository extends JpaRepository<CVEntity, Long> {
    List<CVEntity> findByUserId_Id(Long id);

}
