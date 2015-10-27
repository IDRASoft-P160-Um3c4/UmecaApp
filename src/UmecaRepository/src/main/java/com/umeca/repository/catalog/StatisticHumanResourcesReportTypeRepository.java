package com.umeca.repository.catalog;

import com.umeca.model.catalog.StatisticHumanResourcesReportType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public interface StatisticHumanResourcesReportTypeRepository extends JpaRepository<StatisticHumanResourcesReportType,Long> {

    @Query("SELECT e from StatisticHumanResourcesReportType e where e.name=:code")
    public StatisticHumanResourcesReportType findByCode(@Param("code") String code);


    @Query("select new com.umeca.model.shared.SelectList(SRT.id, SRT.name, SRT.description) from StatisticHumanResourcesReportType SRT where SRT.isObsolete=false")
    List<SelectList> getAllNoObsolete();




    //Reporte2
    @Query("select new com.umeca.model.shared.SelectList(count(attend.id))" +
            "from AttendanceLog attend " +
            "inner join attend.employee emp " +
            "inner join attend.delayJustification delayJus " +
            "where delayJus.approved = 0 and (attend.eventTime between :initDate and :endDate)")
    List<SelectList> countEmployeeDelays(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate);

    @Query("select new com.umeca.model.shared.SelectList(count(attend.id))" +
            "from AttendanceLog attend " +
            "inner join attend.employee emp " +
            "inner join attend.delayJustification delayJus " +
            "where emp.district.id = :idDistrict " +
            "and delayJus.approved = 0 " +
            "and (attend.eventTime between :initDate and :endDate)")
    List<SelectList> countEmployeeDelaysByDistrict(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("idDistrict") Long idDistrict);

    @Query("select new com.umeca.model.shared.SelectList(count(attend.id))" +
            "from AttendanceLog attend " +
            "inner join attend.employee emp " +
            "inner join attend.delayJustification delayJus " +
            "where emp.id = :idEmployee " +
            "and delayJus.approved = 0 " +
            "and (attend.eventTime between :initDate and :endDate)")
    List<SelectList> countEmployeeDelaysByOperator(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("idEmployee") Long idEmployee);




}
