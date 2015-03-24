package com.umeca.repository.humanResources;

import com.umeca.model.catalog.IncidentType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qIncidentTypeRepository")
public interface IncidentTypeRepository extends JpaRepository<IncidentType, Long> {

    @Query("select new com.umeca.model.shared.SelectList(IT.id,IT.name,IT.specification) from IncidentType IT " +
            "where IT.isObsolete=false order by IT.name asc")
    List<SelectList> findNoObsolete();

}