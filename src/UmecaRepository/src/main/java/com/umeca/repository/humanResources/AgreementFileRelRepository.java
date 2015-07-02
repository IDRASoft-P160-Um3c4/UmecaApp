package com.umeca.repository.humanResources;

import com.umeca.model.entities.humanReources.AgreementFileRel;
import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.shared.UploadFileGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qAgreementFileRelRepository")
public interface AgreementFileRelRepository extends JpaRepository<AgreementFileRel, Long> {


    @Query("select UF from AgreementFileRel AFR " +
            "inner join AFR.uploadFileGeneric UF " +
            "inner join AFR.agreement A " +
            "where A.id=:agreementId and UF.isObsolete=false")
    List<UploadFileGeneric> getAgreementFilesByAgreementId(@Param("agreementId") Long agreementId);

}