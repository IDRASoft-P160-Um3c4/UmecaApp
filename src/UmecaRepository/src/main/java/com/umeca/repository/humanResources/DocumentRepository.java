package com.umeca.repository.humanResources;

import com.umeca.model.dto.humanResources.DocumentDto;
import com.umeca.model.entities.humanReources.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qDocumentRepository")
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("select new com.umeca.model.dto.humanResources.DocumentDto(DOC.id, DOC.documentDate, DOC.numberDocument, DOC.sender, DOC.receiver, DOC.criminalCause, DOC.subject, DOC.turnedOver, DOC.finalAction) from Document DOC " +
            "where DOC.id=:documentId")
    DocumentDto getDocumentByDocumentId(@Param("documentId") Long documentId);

}