package com.umeca.repository.catalog;
import com.umeca.model.catalog.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("degreeRepository")
public interface DegreeRepository extends JpaRepository<Degree,Long> {

    @Query("select ay from Degree as ay where ay.isObsolete=false and ay.academicLevel.isObsolete=false")
    List<Degree> findNotObsolete();

}
