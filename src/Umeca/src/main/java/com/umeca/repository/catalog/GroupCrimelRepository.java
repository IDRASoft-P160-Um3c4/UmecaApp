package com.umeca.repository.catalog;
import com.umeca.model.catalog.AcademicLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("academicLevelRepository")
public interface GroupCrimelRepository extends JpaRepository<AcademicLevel,Long> {

    @Query("select ad from AcademicLevel as ad where ad.isObsolete=false")
    List<AcademicLevel> findNotObsolete();

}
