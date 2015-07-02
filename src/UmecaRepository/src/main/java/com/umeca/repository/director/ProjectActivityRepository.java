package com.umeca.repository.director;

import com.umeca.model.entities.director.project.ProjectActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProjectActivityRepository extends JpaRepository<ProjectActivity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ProjectActivity PA SET PA.isObsolete = 1 WHERE PA.project.id = :projectId")
    void setObsoleteToActivitiesByProjectId(@Param("projectId") Long id);
}
