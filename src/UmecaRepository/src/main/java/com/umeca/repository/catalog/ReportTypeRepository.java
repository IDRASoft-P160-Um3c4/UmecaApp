package com.umeca.repository.catalog;

import com.umeca.model.catalog.ReportType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by DeveloperII on 09/09/2015.
 */
public interface ReportTypeRepository extends JpaRepository<ReportType,Long> {
    @Query("select new com.umeca.model.shared.SelectList(RT.id, RT.name, RT.description) from ReportType RT where RT.isObsolete=false")
    List<SelectList> getAllNoObsolete();

    @Query("select RT.name from ReportType RT where rt.id = :idReportType")
    String getReportCodeById(@Param("idReportType") Long idReportType);

}
