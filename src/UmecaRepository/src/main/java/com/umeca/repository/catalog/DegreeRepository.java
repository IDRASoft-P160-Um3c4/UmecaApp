package com.umeca.repository.catalog;
import com.umeca.model.catalog.Degree;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("degreeRepository")
public interface DegreeRepository extends JpaRepository<Degree,Long> {

    @Query("select ay from Degree as ay where ay.isObsolete=false and ay.academicLevel.isObsolete=false")
    List<Degree> findNotObsolete();

    @Query("select new com.umeca.model.shared.SelectList(DG.id, DG.name) from Degree DG " +
            "inner join DG.academicLevel AL where DG.isObsolete=false and AL.id=:idAcLvl")
    List<SelectList> findNoObsoleteByAcademicLvlId(@Param("idAcLvl") Long acLvl);


}
