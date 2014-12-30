package com.umeca.repository.catalog;

import com.umeca.model.catalog.ImmigrationDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("immigrationDocumentRepository")
public interface ImmigrationDocumentRepository extends JpaRepository<ImmigrationDocument,Long> {

    @Query("select  e from ImmigrationDocument e where e.obsolete = false")
    List<ImmigrationDocument> findNotObsolete();
}
