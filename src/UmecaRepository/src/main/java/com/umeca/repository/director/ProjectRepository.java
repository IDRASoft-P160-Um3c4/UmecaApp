package com.umeca.repository.director;

import com.umeca.model.entities.director.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT P.name FROM Project P WHERE P.id = :projectId")
    List<String> getProjectNameById(@Param("projectId")Long id);
}
