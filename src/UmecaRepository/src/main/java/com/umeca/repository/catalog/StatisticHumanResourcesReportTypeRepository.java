package com.umeca.repository.catalog;

import com.umeca.model.catalog.StatisticHumanResourcesReportType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;


public interface StatisticHumanResourcesReportTypeRepository extends JpaRepository<StatisticHumanResourcesReportType, Long> {

    @Query("SELECT e from StatisticHumanResourcesReportType e where e.name=:code")
    public StatisticHumanResourcesReportType findByCode(@Param("code") String code);


    @Query("select new com.umeca.model.shared.SelectList(SRT.id, SRT.name, SRT.description) from StatisticHumanResourcesReportType SRT where SRT.isObsolete=false")
    List<SelectList> getAllNoObsolete();


    //Reporte1
    @Query(value = "select mes, periodo, SUM(total) from ( " +
            "select 1 mes, 1 periodo, 0 total union " +
            "select 1, 2, 0 union " +
            "select 2, 1, 0 union " +
            "select 2, 2, 0 union " +
            "select 3, 1, 0 union " +
            "select 3, 2, 0 union " +
            "select 4, 1, 0 union " +
            "select 4, 2, 0 union " +
            "select 5, 1, 0 union " +
            "select 5, 2, 0 union " +
            "select 6, 1, 0 union " +
            "select 6, 2, 0 union " +
            "select 7, 1, 0 union " +
            "select 7, 2, 0 union " +
            "select 8, 1, 0 union " +
            "select 8, 2, 0 union " +
            "select 9, 1, 0 union " +
            "select 9, 2, 0 union " +
            "select 10, 1, 0 union " +
            "select 10, 2, 0 union " +
            "select 11, 1, 0 union " +
            "select 11, 2, 0 union " +
            "select 12, 1, 0 union " +
            "select 12, 2, 0 union " +
            "select " +
            "IFNULL(month,1), " +
            "case " +
            "WHEN day >= p1 AND day < p2 THEN CONCAT(1) " +
            "WHEN p1 = 1 AND day >= p2 THEN CONCAT(2) " +
            "WHEN p1 > 1 AND day < p1 THEN CONCAT(2) " +
            "else 1 " +
            "end as date_range, count(*) as count " +
            "from " +
            "(select YEAR(absen.absence_date) year, " +
            "   MONTH(absen.absence_date) month, " +
            "   DAY(absen.absence_date) day, " +
            "   (SELECT value_setting " +
            "   FROM system_setting " +
            "   WHERE group_setting = 'ATTENDANCE' " +
            "   AND key_setting = 'PeriodStart') p1, " +
            "   (SELECT value_setting " +
            "   FROM system_setting" +
            "   WHERE group_setting = 'ATTENDANCE' " +
            "   AND key_setting = 'PeriodEnd') p2 " +
            "   from absence absen " +
            "   inner join employee emp on absen.id_employee = emp.id_employee " +
            "   where (absen.absence_date between :initDate and :endDate)" +
            ") as query ) query2 " +
            "where mes between :monthI and :monthF " +
            "GROUP by mes, periodo", nativeQuery = true)
    List<Object> countEmployeeAbsence(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("monthI") Integer monthI, @Param("monthF") Integer monthF);


    @Query(value = "select mes, periodo, SUM(total) from ( " +
            "select 1 mes, 1 periodo, 0 total union " +
            "select 1, 2, 0 union " +
            "select 2, 1, 0 union " +
            "select 2, 2, 0 union " +
            "select 3, 1, 0 union " +
            "select 3, 2, 0 union " +
            "select 4, 1, 0 union " +
            "select 4, 2, 0 union " +
            "select 5, 1, 0 union " +
            "select 5, 2, 0 union " +
            "select 6, 1, 0 union " +
            "select 6, 2, 0 union " +
            "select 7, 1, 0 union " +
            "select 7, 2, 0 union " +
            "select 8, 1, 0 union " +
            "select 8, 2, 0 union " +
            "select 9, 1, 0 union " +
            "select 9, 2, 0 union " +
            "select 10, 1, 0 union " +
            "select 10, 2, 0 union " +
            "select 11, 1, 0 union " +
            "select 11, 2, 0 union " +
            "select 12, 1, 0 union " +
            "select 12, 2, 0 union " +
            "select " +
            "IFNULL(month,1), " +
            "case " +
            "WHEN day >= p1 AND day < p2 THEN CONCAT(1) " +
            "WHEN p1 = 1 AND day >= p2 THEN CONCAT(2) " +
            "WHEN p1 > 1 AND day < p1 THEN CONCAT(2) " +
            "else 1 " +
            "end as date_range, count(*) as count " +
            "from " +
            "(select YEAR(absen.absence_date) year, " +
            "   MONTH(absen.absence_date) month, " +
            "   DAY(absen.absence_date) day, " +
            "   (SELECT value_setting " +
            "   FROM system_setting " +
            "   WHERE group_setting = 'ATTENDANCE' " +
            "   AND key_setting = 'PeriodStart') p1, " +
            "   (SELECT value_setting " +
            "   FROM system_setting" +
            "   WHERE group_setting = 'ATTENDANCE' " +
            "   AND key_setting = 'PeriodEnd') p2 " +
            "   from absence absen " +
            "   inner join employee emp on absen.id_employee = emp.id_employee " +
            "   where emp.id_district = :idDistrict " +
            "   and (absen.absence_date between :initDate and :endDate)" +
            ") as query ) query2 " +
            "where mes between :monthI and :monthF " +
            "GROUP by mes, periodo", nativeQuery = true)
    List<Object> countEmployeeAbsenceByDistrict(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("idDistrict") Long idDistrict, @Param("monthI") Integer monthI, @Param("monthF") Integer monthF);

    @Query(value = "select mes, periodo, SUM(total) from ( " +
            "select 1 mes, 1 periodo, 0 total union " +
            "select 1, 2, 0 union " +
            "select 2, 1, 0 union " +
            "select 2, 2, 0 union " +
            "select 3, 1, 0 union " +
            "select 3, 2, 0 union " +
            "select 4, 1, 0 union " +
            "select 4, 2, 0 union " +
            "select 5, 1, 0 union " +
            "select 5, 2, 0 union " +
            "select 6, 1, 0 union " +
            "select 6, 2, 0 union " +
            "select 7, 1, 0 union " +
            "select 7, 2, 0 union " +
            "select 8, 1, 0 union " +
            "select 8, 2, 0 union " +
            "select 9, 1, 0 union " +
            "select 9, 2, 0 union " +
            "select 10, 1, 0 union " +
            "select 10, 2, 0 union " +
            "select 11, 1, 0 union " +
            "select 11, 2, 0 union " +
            "select 12, 1, 0 union " +
            "select 12, 2, 0 union " +
            "select " +
            "IFNULL(month,1), " +
            "case " +
            "WHEN day >= p1 AND day < p2 THEN CONCAT(1) " +
            "WHEN p1 = 1 AND day >= p2 THEN CONCAT(2) " +
            "WHEN p1 > 1 AND day < p1 THEN CONCAT(2) " +
            "else 1 " +
            "end as date_range, count(*) as count " +
            "from " +
            "(select YEAR(absen.absence_date) year, " +
            "   MONTH(absen.absence_date) month, " +
            "   DAY(absen.absence_date) day, " +
            "   (SELECT value_setting " +
            "   FROM system_setting " +
            "   WHERE group_setting = 'ATTENDANCE' " +
            "   AND key_setting = 'PeriodStart') p1, " +
            "   (SELECT value_setting " +
            "   FROM system_setting" +
            "   WHERE group_setting = 'ATTENDANCE' " +
            "   AND key_setting = 'PeriodEnd') p2 " +
            "   from absence absen " +
            "   inner join employee emp on absen.id_employee = emp.id_employee " +
            "   where emp.id_employee = :idEmployee " +
            "   and (absen.absence_date between :initDate and :endDate)" +
            ") as query ) query2 " +
            "where mes between :monthI and :monthF " +
            "GROUP by mes, periodo", nativeQuery = true)
    List<Object> countEmployeeAbsenceByOperator(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("idEmployee") Long idEmployee, @Param("monthI") Integer monthI, @Param("monthF") Integer monthF);


    //Reporte2
    @Query(value = "select mes, periodo, SUM(total) from ( " +
            "select 1 mes, 1 periodo, 0 total union " +
            "select 1, 2, 0 union " +
            "select 2, 1, 0 union " +
            "select 2, 2, 0 union " +
            "select 3, 1, 0 union " +
            "select 3, 2, 0 union " +
            "select 4, 1, 0 union " +
            "select 4, 2, 0 union " +
            "select 5, 1, 0 union " +
            "select 5, 2, 0 union " +
            "select 6, 1, 0 union " +
            "select 6, 2, 0 union " +
            "select 7, 1, 0 union " +
            "select 7, 2, 0 union " +
            "select 8, 1, 0 union " +
            "select 8, 2, 0 union " +
            "select 9, 1, 0 union " +
            "select 9, 2, 0 union " +
            "select 10, 1, 0 union " +
            "select 10, 2, 0 union " +
            "select 11, 1, 0 union " +
            "select 11, 2, 0 union " +
            "select 12, 1, 0 union " +
            "select 12, 2, 0 union " +
            "select " +
            "IFNULL(month,1), " +
            "case " +
            "WHEN day >= p1 AND day < p2 THEN CONCAT(1) " +
            "WHEN p1 = 1 AND day >= p2 THEN CONCAT(2) " +
            "WHEN p1 > 1 AND day < p1 THEN CONCAT(2) " +
            "else 1 " +
            "end as date_range, count(*) as count " +
            "from " +
            "(select YEAR(attend.eventtime) year, " +
            "   MONTH(attend.eventtime) month, " +
            "   DAY(attend.eventtime) day, " +
            "   (SELECT value_setting " +
            "   FROM system_setting " +
            "   WHERE group_setting = 'ATTENDANCE' " +
            "   AND key_setting = 'PeriodStart') p1, " +
            "   (SELECT value_setting " +
            "   FROM system_setting" +
            "   WHERE group_setting = 'ATTENDANCE' " +
            "   AND key_setting = 'PeriodEnd') p2 " +
            "   from attendancelog attend " +
            "   inner join employee emp on attend.id_employee = emp.id_employee " +
            "   inner join delay_justification delayJus on attend.id_attendancelog = delayJus.id_attendancelog " +
            "   where delayJus.approved = 0 " +
            "   and (attend.eventtime between :initDate and :endDate)" +
            ") as query ) query2 " +
            "where mes between :monthI and :monthF " +
            "GROUP by mes, periodo", nativeQuery = true)
    List<Object> countEmployeeDelays(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("monthI") Integer monthI, @Param("monthF") Integer monthF);

    @Query(value = "select mes, periodo, SUM(total) from ( " +
            "select 1 mes, 1 periodo, 0 total union " +
            "select 1, 2, 0 union " +
            "select 2, 1, 0 union " +
            "select 2, 2, 0 union " +
            "select 3, 1, 0 union " +
            "select 3, 2, 0 union " +
            "select 4, 1, 0 union " +
            "select 4, 2, 0 union " +
            "select 5, 1, 0 union " +
            "select 5, 2, 0 union " +
            "select 6, 1, 0 union " +
            "select 6, 2, 0 union " +
            "select 7, 1, 0 union " +
            "select 7, 2, 0 union " +
            "select 8, 1, 0 union " +
            "select 8, 2, 0 union " +
            "select 9, 1, 0 union " +
            "select 9, 2, 0 union " +
            "select 10, 1, 0 union " +
            "select 10, 2, 0 union " +
            "select 11, 1, 0 union " +
            "select 11, 2, 0 union " +
            "select 12, 1, 0 union " +
            "select 12, 2, 0 union " +
            "select " +
            "IFNULL(month,1), " +
            "case " +
            "WHEN day >= p1 AND day < p2 THEN CONCAT(1) " +
            "WHEN p1 = 1 AND day >= p2 THEN CONCAT(2) " +
            "WHEN p1 > 1 AND day < p1 THEN CONCAT(2) " +
            "else 1 " +
            "end as date_range, count(*) as count " +
            "from " +
            "(select YEAR(attend.eventtime) year, " +
            "   MONTH(attend.eventtime) month, " +
            "   DAY(attend.eventtime) day, " +
            "   (SELECT value_setting " +
            "   FROM system_setting " +
            "   WHERE group_setting = 'ATTENDANCE' " +
            "   AND key_setting = 'PeriodStart') p1, " +
            "   (SELECT value_setting " +
            "   FROM system_setting" +
            "   WHERE group_setting = 'ATTENDANCE' " +
            "   AND key_setting = 'PeriodEnd') p2 " +
            "   from attendancelog attend " +
            "   inner join employee emp on attend.id_employee = emp.id_employee " +
            "   inner join delay_justification delayJus on attend.id_attendancelog = delayJus.id_attendancelog " +
            "   where emp.id_district = :idDistrict " +
            "   and delayJus.approved = 0 " +
            "   and (attend.eventtime between :initDate and :endDate)" +
            ") as query ) query2 " +
            "where mes between :monthI and :monthF " +
            "GROUP by mes, periodo", nativeQuery = true)
    List<Object> countEmployeeDelaysByDistrict(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("idDistrict") Long idDistrict, @Param("monthI") Integer monthI, @Param("monthF") Integer monthF);


    @Query(value = "select mes, periodo, SUM(total) from ( " +
            "select 1 mes, 1 periodo, 0 total union " +
            "select 1, 2, 0 union " +
            "select 2, 1, 0 union " +
            "select 2, 2, 0 union " +
            "select 3, 1, 0 union " +
            "select 3, 2, 0 union " +
            "select 4, 1, 0 union " +
            "select 4, 2, 0 union " +
            "select 5, 1, 0 union " +
            "select 5, 2, 0 union " +
            "select 6, 1, 0 union " +
            "select 6, 2, 0 union " +
            "select 7, 1, 0 union " +
            "select 7, 2, 0 union " +
            "select 8, 1, 0 union " +
            "select 8, 2, 0 union " +
            "select 9, 1, 0 union " +
            "select 9, 2, 0 union " +
            "select 10, 1, 0 union " +
            "select 10, 2, 0 union " +
            "select 11, 1, 0 union " +
            "select 11, 2, 0 union " +
            "select 12, 1, 0 union " +
            "select 12, 2, 0 union " +
            "select " +
            "IFNULL(month,1), " +
            "case " +
            "WHEN day >= p1 AND day < p2 THEN CONCAT(1) " +
            "WHEN p1 = 1 AND day >= p2 THEN CONCAT(2) " +
            "WHEN p1 > 1 AND day < p1 THEN CONCAT(2) " +
            "else 1 " +
            "end as date_range, count(*) as count " +
            "from " +
            "(select YEAR(attend.eventtime) year, " +
            "   MONTH(attend.eventtime) month, " +
            "   DAY(attend.eventtime) day, " +
            "   (SELECT value_setting " +
            "   FROM system_setting " +
            "   WHERE group_setting = 'ATTENDANCE' " +
            "   AND key_setting = 'PeriodStart') p1, " +
            "   (SELECT value_setting " +
            "   FROM system_setting" +
            "   WHERE group_setting = 'ATTENDANCE' " +
            "   AND key_setting = 'PeriodEnd') p2 " +
            "   from attendancelog attend " +
            "   inner join employee emp on attend.id_employee = emp.id_employee " +
            "   inner join delay_justification delayJus on attend.id_attendancelog = delayJus.id_attendancelog " +
            "   where emp.id_employee = :idEmployee " +
            "   and delayJus.approved = 0 " +
            "   and (attend.eventtime between :initDate and :endDate)" +
            ") as query ) query2 " +
            "where mes between :monthI and :monthF " +
            "GROUP by mes, periodo", nativeQuery = true)
    List<Object> countEmployeeDelaysByOperator(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("idEmployee") Long idEmployee, @Param("monthI") Integer monthI, @Param("monthF") Integer monthF);


    //Reporte3
    @Query("select new com.umeca.model.shared.SelectList(case when delayJus.approved = 0 then 'Horas extras' else 'Horas extras' end,count(attend.id))" +
            "from AttendanceLog attend " +
            "inner join attend.employee emp " +
            "inner join attend.delayJustification delayJus " +
            "where delayJus.approved = 0 and (attend.eventTime between :initDate and :endDate)")
    List<SelectList> countEmployeeBonusTime(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate);

    @Query("select new com.umeca.model.shared.SelectList(case when delayJus.approved = 0 then 'Horas extras' else 'Horas extras' end,count(attend.id))" +
            "from AttendanceLog attend " +
            "inner join attend.employee emp " +
            "inner join attend.delayJustification delayJus " +
            "where emp.district.id = :idDistrict " +
            "and delayJus.approved = 0 " +
            "and (attend.eventTime between :initDate and :endDate)")
    List<SelectList> countEmployeeBonusTimeByDistrict(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("idDistrict") Long idDistrict);

    @Query("select new com.umeca.model.shared.SelectList(case when delayJus.approved = 0 then 'Horas extras' else 'Horas extras' end,count(attend.id))" +
            "from AttendanceLog attend " +
            "inner join attend.employee emp " +
            "inner join attend.delayJustification delayJus " +
            "where emp.id = :idEmployee " +
            "and delayJus.approved = 0 " +
            "and (attend.eventTime between :initDate and :endDate)")
    List<SelectList> countEmployeeBonusTimeByOperator(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("idEmployee") Long idEmployee);


}