package com.umeca.repository.director;

import com.umeca.model.dto.shared.AgreementDto;
import com.umeca.model.entities.director.minutes.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qAgreementRepository")
public interface AgreementRepository extends JpaRepository<Agreement, Long> {


    @Query("select new com.umeca.model.dto.shared.AgreementDto(AG.title, AG.theme, AG.agreementDate, AR.name, " +
            "AR.specification, AG.specArea, AG.isFinished, AG.isDone) from Agreement AG " +
            "inner join AG.area AR " +
            "where AG.id=:agreementId")
    public AgreementDto getGrlAgreementInfoById(@Param("agreementId") Long agreementId);
}
