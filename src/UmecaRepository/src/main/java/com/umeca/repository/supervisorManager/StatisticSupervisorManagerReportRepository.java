package com.umeca.repository.supervisorManager;

import com.umeca.model.catalog.Question;
import com.umeca.model.catalog.StatisticReportType;
import com.umeca.model.catalog.StatisticSupervisorManagerReportType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DeveloperII on 04/09/2015.
 */
@Repository("qStatisticSupervisorManagerReportRepository")
public interface StatisticSupervisorManagerReportRepository  extends JpaRepository<StatisticSupervisorManagerReportType, Long>  {


    @Query("SELECT e from StatisticSupervisorManagerReportType e where e.name=:code")
    public StatisticSupervisorManagerReportType findByCode(@Param("code") String code);

    @Query("select new com.umeca.model.shared.SelectList(SSMRT.id, SSMRT.name, SSMRT.description) from StatisticSupervisorManagerReportType SSMRT where SSMRT.isObsolete=false")
    List<SelectList> getAllNoObsolete();
}
