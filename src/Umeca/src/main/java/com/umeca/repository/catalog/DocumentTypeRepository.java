package com.umeca.repository.catalog;
import com.umeca.model.catalog.DocumentType;
import com.umeca.model.catalog.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("documentTypeRepository")
public interface DocumentTypeRepository extends JpaRepository<DocumentType,Long> {

}
