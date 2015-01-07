package com.umeca.repository.reviewer;

import com.umeca.model.entities.reviewer.CurrentCriminalProceeding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("currentCriminalProceedingRepository")
public interface CurrentCriminalProceedingRepository extends JpaRepository<CurrentCriminalProceeding,Long> {

    @Query("select ccp from CurrentCriminalProceeding ccp " +
            "inner join ccp.meeting as m " +
            "inner join m.caseDetention cd " +
            "where cd.id = :idCase")
    CurrentCriminalProceeding findByIdCase(@Param("idCase")Long idCase);
}
