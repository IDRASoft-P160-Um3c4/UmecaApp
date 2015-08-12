package com.umeca.repository.humanResources;

import com.umeca.model.entities.humanReources.RelDocumentUploadGenericFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qRelDocumentUploadGenericFileRepository")
public interface RelDocumentUploadGenericFileRepository extends JpaRepository<RelDocumentUploadGenericFile, Long> {

}