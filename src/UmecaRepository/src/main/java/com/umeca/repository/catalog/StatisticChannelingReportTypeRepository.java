package com.umeca.repository.catalog;

import com.umeca.model.catalog.StatisticChannelingReportType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StatisticChannelingReportTypeRepository extends JpaRepository<StatisticChannelingReportType,Long> {

    @Query("SELECT e from StatisticChannelingReportType e where e.name=:code")
    public StatisticChannelingReportType findByCode(@Param("code") String code);


    @Query("select new com.umeca.model.shared.SelectList(SRT.id, SRT.name, SRT.description) from StatisticChannelingReportType SRT where SRT.isObsolete=false")
    List<SelectList> getAllNoObsolete();

}
