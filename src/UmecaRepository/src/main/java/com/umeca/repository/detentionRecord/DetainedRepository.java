package com.umeca.repository.detentionRecord;

import com.umeca.model.dto.shared.AgreementDto;
import com.umeca.model.entities.detentionRecord.Detained;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qDetainedRepository")
public interface DetainedRepository extends JpaRepository<Detained, Long> {

    @Query("select new com.umeca.model.dto.shared.AgreementDto(AG.title, AG.theme, AG.agreementDate, AR.name, " +
            "AR.specification, AG.specArea, AG.isFinished, AG.isDone) from Minute M " +
            "inner join M.agreements AG " +
            "inner join AG.area AR " +
            "where M.id=:minuteId order by AR.name")
    public List<AgreementDto> getAgreementsInfoByMinuteId(@Param("minuteId") Long minuteId);

}
