package com.umeca.repository.humanResources;

import com.umeca.model.dto.timeAttendance.FingerPrintWSDto;
import com.umeca.model.entities.fingerprint.ImputedFingerprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 3/31/2016.
 */
@Repository
public interface ImputedFingerPrintRepository extends JpaRepository<ImputedFingerprint, Long> {
    @Query("select  new com.umeca.model.dto.timeAttendance.FingerPrintWSDto(ef.finger, ef.data) " +
            "from ImputedFingerprint ef " +
            "inner join ef.imputed Em " +
            "where Em.id = :idImputed")
    List<FingerPrintWSDto> getFingerPrints(@Param("idImputed") Long idEmployee);


    @Query("select ef " +
            "from ImputedFingerprint ef " +
            "inner join ef.imputed Em " +
            "where Em.id = :idImputed and ef.finger = :finger")
    ImputedFingerprint getFingerPrint(@Param("idImputed") Long idImputed, @Param("finger") short finger);
}
