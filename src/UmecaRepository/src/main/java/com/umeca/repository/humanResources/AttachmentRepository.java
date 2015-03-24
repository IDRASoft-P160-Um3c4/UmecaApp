package com.umeca.repository.humanResources;

import com.umeca.model.dto.humanResources.AttachmentDto;
import com.umeca.model.entities.humanReources.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qAttachmentRepository")
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    @Query("select new com.umeca.model.dto.humanResources.AttachmentDto(ATT.id,GF.id,E.id,ATT.attachmentName,ATT.attachmentDescription) from Attachment ATT " +
            "inner join ATT.employee E " +
            "inner join ATT.genericFile GF " +
            "where E.id=:idEmployee and ATT.id=:idAttachment")
    AttachmentDto findAttachmentDtoByIds(@Param("idEmployee") Long idEmployee, @Param("idAttachment") Long idAttachment);


    @Query("select ATT from Attachment ATT " +
            "inner join ATT.employee E " +
            "where E.id=:idEmployee and ATT.id=:idAttachment")
    Attachment findAttachmentByIds(@Param("idEmployee") Long idEmployee, @Param("idAttachment") Long idAttachment);

}