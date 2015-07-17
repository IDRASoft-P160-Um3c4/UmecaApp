package com.umeca.repository.catalog;
import com.umeca.model.catalog.AcademicLevel;
import com.umeca.model.catalog.InformationAvailability;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("informationAvailabilityRepository")
public interface InformationAvailabilityRepository extends JpaRepository<InformationAvailability,Long> {
    @Query("select new com.umeca.model.shared.SelectList(ia.id, ia.name, ia.specification) from InformationAvailability as ia where ia.isObsolete=false")
    List<SelectList> findNoObsolete();
}
