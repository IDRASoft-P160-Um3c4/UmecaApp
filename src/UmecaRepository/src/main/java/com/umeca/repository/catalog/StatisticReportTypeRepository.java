package com.umeca.repository.catalog;

import com.umeca.model.catalog.EventType;
import com.umeca.model.catalog.StatisticReportType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface StatisticReportTypeRepository extends JpaRepository<StatisticReportType,Long> {

    @Query("SELECT e from StatisticReportType e where e.name=:code")
    public StatisticReportType findByCode(@Param("code") String code);


    @Query("select new com.umeca.model.shared.SelectList(SRT.id, SRT.name, SRT.description) from StatisticReportType SRT where SRT.isObsolete=false")
    List<SelectList> getAllNoObsolete();

}
