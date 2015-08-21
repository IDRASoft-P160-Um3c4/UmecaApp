package com.umeca.repository.catalog;

import com.umeca.model.catalog.StatisticOperatorReportType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StatisticOperatorReportTypeRepository extends JpaRepository<StatisticOperatorReportType,Long> {

    @Query("SELECT e from StatisticOperatorReportType e where e.name=:code")
    public StatisticOperatorReportType findByCode(@Param("code") String code);


    @Query("select new com.umeca.model.shared.SelectList(SRT.id, SRT.name, SRT.description) from StatisticOperatorReportType SRT where SRT.isObsolete=false")
    List<SelectList> getAllNoObsolete();

}
