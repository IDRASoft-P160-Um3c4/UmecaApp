package com.umeca.repository.reviewer;

import com.umeca.model.entities.reviewer.Crime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("crimeRepository")
public interface CrimeRepository extends JpaRepository<Crime,Long> {

    @Query("select cr from Crime as cr " +
            "inner join cr.criminalProceeding as ccp " +
            "inner join ccp.meeting as m " +
            "inner join m.caseDetention as c where c.id =:idCase")
    List<Crime> findListCrimeLegalByIdCase(@Param("idCase")Long id);


    @Query("select cr from Crime as cr " +
            "inner join cr.hearingFormat as hf " +
            "where hf.id=:id")
    List<Crime> findListCrimeHearingFormatByIdHF(@Param("id") Long id);
}
