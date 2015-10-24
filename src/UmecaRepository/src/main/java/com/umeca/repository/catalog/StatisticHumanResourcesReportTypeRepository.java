package com.umeca.repository.catalog;

import com.umeca.model.catalog.StatisticHumanResourcesReportType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StatisticHumanResourcesReportTypeRepository extends JpaRepository<StatisticHumanResourcesReportType,Long> {

    @Query("SELECT e from StatisticReportType e where e.name=:code")
    public StatisticHumanResourcesReportType findByCode(@Param("code") String code);


    @Query("select new com.umeca.model.shared.SelectList(SRT.id, SRT.name, SRT.description) from StatisticHumanResourcesReportType SRT where SRT.isObsolete=false")
    List<SelectList> getAllNoObsolete();

}
