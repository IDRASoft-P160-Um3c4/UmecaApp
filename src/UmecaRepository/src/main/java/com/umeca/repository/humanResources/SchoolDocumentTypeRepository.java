package com.umeca.repository.humanResources;

import com.umeca.model.entities.shared.SchoolDocumentType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qSchoolDocumentTypeRepository")
public interface SchoolDocumentTypeRepository extends JpaRepository<SchoolDocumentType, Long> {

    @Query("select new com.umeca.model.shared.SelectList(SDT.id,SDT.name,SDT.specification) from SchoolDocumentType SDT " +
            "where SDT.isObsolete=false order by SDT.name asc")
    List<SelectList> findNoObsolete();
}