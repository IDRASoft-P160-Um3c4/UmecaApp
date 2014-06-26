package com.umeca.repository.catalog;
import com.umeca.model.catalog.DocumentType;
import com.umeca.model.catalog.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("documentTypeRepository")
public interface DocumentTypeRepository extends JpaRepository<DocumentType,Long> {

    @Query("select dt from DocumentType as dt where dt.isObsolete=false")
    List<DocumentType> findNotObsolete();

}
