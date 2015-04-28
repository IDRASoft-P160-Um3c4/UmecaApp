package com.umeca.repository.catalog;

import com.umeca.model.catalog.CatEducationLevel;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("educationLevelRepository")
public interface EducationLevelRepository extends JpaRepository<CatEducationLevel, Long> {
    @Query("select new com.umeca.model.shared.SelectList(a.id, a.name) from CatEducationLevel a where a.isObsolete=false")
    List<SelectList> findNotObsolete();
}
