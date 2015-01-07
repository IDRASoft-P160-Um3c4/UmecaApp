package com.umeca.repository.catalog;

import com.umeca.model.catalog.FulfillmentReportType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("fulfillmentReportTypeRepository")
public interface FulfillmentReportTypeRepository extends JpaRepository<FulfillmentReportType,Long> {

    @Query("SELECT NEW com.umeca.model.shared.SelectList(f.id, f.name) FROM FulfillmentReportType AS f WHERE f.isObsolete=false")
    List<SelectList> findNotObsolete();

    @Query("SELECT f FROM FulfillmentReportType AS f WHERE f.code=:code")
    FulfillmentReportType findByCode(@Param("code") String code);
}
