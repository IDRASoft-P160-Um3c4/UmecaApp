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
            "case " +
            "when day >= p1 " +
            "and day < p2 then month " +
            "when p1 = 1 " +
            "and day >= p2 then month " +
            "when p1 > 1 " +
            "and day < p1 then (month - 1) " +
            "else 1 " +
            "end, " +
            "case " +
            "when day >= p1 " +
            "and day < p2 then 1 " +
            "when p1 = 1 " +
            "and day >= p2 then 2 " +
            "when p1 > 1 " +
            "and day < p1 then 2 " +
            "else 1 " +
            "end as date_range, " +
            "count(*) as count " +
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
            ") as query " +
            "group by month, date_range) query2 " +
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
            "case " +
            "when day >= p1 " +
            "and day < p2 then month " +
            "when p1 = 1 " +
            "and day >= p2 then month " +
            "when p1 > 1 " +
            "and day < p1 then (month - 1) " +
            "else 1 " +
            "end, " +
            "case " +
            "when day >= p1 " +
            "and day < p2 then 1 " +
            "when p1 = 1 " +
            "and day >= p2 then 2 " +
            "when p1 > 1 " +
            "and day < p1 then 2 " +
            "else 1 " +
            "end as date_range, " +
            "count(*) as count " +
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
            ") as query " +
            "group by month, date_range) query2 " +
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
            "case " +
            "when day >= p1 " +
            "and day < p2 then month " +
            "when p1 = 1 " +
            "and day >= p2 then month " +
            "when p1 > 1 " +
            "and day < p1 then (month - 1) " +
            "else 1 " +
            "end, " +
            "case " +
            "when day >= p1 " +
            "and day < p2 then 1 " +
            "when p1 = 1 " +
            "and day >= p2 then 2 " +
            "when p1 > 1 " +
            "and day < p1 then 2 " +
            "else 1 " +
            "end as date_range, " +
            "count(*) as count " +
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
            ") as query " +
            "group by month, date_range) query2 " +
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
            "case " +
            "when day >= p1 " +
            "and day < p2 then month " +
            "when p1 = 1 " +
            "and day >= p2 then month " +
            "when p1 > 1 " +
            "and day < p1 then (month - 1) " +
            "else 1 " +
            "end, " +
            "case " +
            "when day >= p1 " +
            "and day < p2 then 1 " +
            "when p1 = 1 " +
            "and day >= p2 then 2 " +
            "when p1 > 1 " +
            "and day < p1 then 2 " +
            "else 1 " +
            "end as date_range, " +
            "count(*) as count " +
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
            ") as query " +
            "group by month, date_range) query2 " +
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
            "case " +
            "when day >= p1 " +
            "and day < p2 then month " +
            "when p1 = 1 " +
            "and day >= p2 then month " +
            "when p1 > 1 " +
            "and day < p1 then (month - 1) " +
            "else 1 " +
            "end, " +
            "case " +
            "when day >= p1 " +
            "and day < p2 then 1 " +
            "when p1 = 1 " +
            "and day >= p2 then 2 " +
            "when p1 > 1 " +
            "and day < p1 then 2 " +
            "else 1 " +
            "end as date_range, " +
            "count(*) as count " +
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
            ") as query " +
            "group by month, date_range) query2 " +
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
            "case " +
            "when day >= p1 " +
            "and day < p2 then month " +
            "when p1 = 1 " +
            "and day >= p2 then month " +
            "when p1 > 1 " +
            "and day < p1 then (month - 1) " +
            "else 1 " +
            "end, " +
            "case " +
            "when day >= p1 " +
            "and day < p2 then 1 " +
            "when p1 = 1 " +
            "and day >= p2 then 2 " +
            "when p1 > 1 " +
            "and day < p1 then 2 " +
            "else 1 " +
            "end as date_range, " +
            "count(*) as count " +
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
            ") as query " +
            "group by month, date_range) query2 " +
            "where mes between :monthI and :monthF " +
            "GROUP by mes, periodo", nativeQuery = true)
    List<Object> countEmployeeDelaysByOperator(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("idEmployee") Long idEmployee, @Param("monthI") Integer monthI, @Param("monthF") Integer monthF);


    //Reporte3
    @Query(value = "select mes, periodo, SUM(total)\n" +
            "from (\n" +
            "select 1 mes, 1 periodo, 0 total union\n" +
            "select 1, 2, 0 union\n" +
            "select 2, 1, 0 union\n" +
            "select 2, 2, 0 union\n" +
            "select 3, 1, 0 union\n" +
            "select 3, 2, 0 union\n" +
            "select 4, 1, 0 union\n" +
            "select 4, 2, 0 union\n" +
            "select 5, 1, 0 union\n" +
            "select 5, 2, 0 union\n" +
            "select 6, 1, 0 union\n" +
            "select 6, 2, 0 union\n" +
            "select 7, 1, 0 union\n" +
            "select 7, 2, 0 union\n" +
            "select 8, 1, 0 union\n" +
            "select 8, 2, 0 union\n" +
            "select 9, 1, 0 union\n" +
            "select 9, 2, 0 union\n" +
            "select 10, 1, 0 union\n" +
            "select 10, 2, 0 union\n" +
            "select 11, 1, 0 union\n" +
            "select 11, 2, 0 union\n" +
            "select 12, 1, 0 union\n" +
            "select 12, 2, 0 union\n" +
            "SELECT\n" +
            "case " +
            "when day >= p1 " +
            "and day < p2 then month " +
            "when p1 = 1 " +
            "and day >= p2 then month " +
            "when p1 > 1 " +
            "and day < p1 then (month - 1) " +
            "else 1 " +
            "end, " +
            "case " +
            "when day >= p1 " +
            "and day < p2 then 1 " +
            "when p1 = 1 " +
            "and day >= p2 then 2 " +
            "when p1 > 1 " +
            "and day < p1 then 2 " +
            "else 1 " +
            "end as date_range, " +
            "        SUM(bonustime)\n" +
            "FROM\n" +
            "    (\n" +
            "    select \n" +
            "    YEAR(extraTime.eventtime) year,\n" +
            "\tMONTH(extraTime.eventtime) month,\n" +
            "\tDAY(extraTime.eventtime) day,\n" +
            "    extraTime.bonustime,\n" +
            "            (SELECT \n" +
            "                    value_setting\n" +
            "                FROM\n" +
            "                    system_setting\n" +
            "                WHERE\n" +
            "                    group_setting = 'ATTENDANCE'\n" +
            "                        AND key_setting = 'PeriodStart') p1,\n" +
            "            (SELECT \n" +
            "                    value_setting\n" +
            "                FROM\n" +
            "                    system_setting\n" +
            "                WHERE\n" +
            "                    group_setting = 'ATTENDANCE'\n" +
            "                        AND key_setting = 'PeriodEnd') p2\n" +
            "    from (select\n" +
            "\t\t\tid_attendancelog,\n" +
            "\t\t\tname,\n" +
            "\t\t\teventtime,\n" +
            "\t\t\tcheckout,\n" +
            "\t\t\touttime,\n" +
            "\t\t\ttolerance,\n" +
            "\t\t\tovertime > 0 approved,\n" +
            "\t\t\tconcat('', sec_to_time(outtime)) textOut, \n" +
            "\t\t\tconcat('', time(eventtime)) textOu,\n" +
            "\t\t\tconcat('', date(eventtime)) textDt, \n" +
            "\t\t\tround((checkout - outtime) / (60 * 60)) bonustime,\n" +
            "\t\t\tid_employee,\n" +
            "\t\t\tworkcode \n" +
            "\t\tfrom (\n" +
            "\t\t\tselect \n" +
            "\t\t\t\ta.id_attendancelog,\n" +
            "\t\t\t\tconcat(e.name, ' ', coalesce(concat(e.last_name_p,' '), ''), coalesce(concat('', e.last_name_m), '')) \"name\",\n" +
            "\t\t\t\ta.eventtime,\n" +
            "\t\t\t\ttime_to_sec(eventtime) checkout,\n" +
            "\t\t\t\tcoalesce((select sd.end from schedule_days sd where sd.day_id = weekday(a.eventtime) + 1 and sd.id_employee_schedule = e.id_employee_schedule), 23 * 60 * 60 + 59 * 60 + 59) \"outtime\",\n" +
            "\t\t\t\tcast(time_to_sec(coalesce((select value_setting from system_setting where group_setting = 'ATTENDANCE' and key_setting = 'ArrivalTolerance'), '00:00:00')) as UNSIGNED) tolerance,\n" +
            "\t\t\t\t(select count(*) > 0 from bonustime bt where bt.id_attendancelog = a.id_attendancelog) overtime,\n" +
            "\t\t\t\ta.id_employee,\n" +
            "\t\t\t\ta.workcode  \n" +
            "\t\t\tfrom attendancelog a   \n" +
            "\t\t\tinner join employee e   \n" +
            "\t\t\t\ton a.id_employee = e.id_employee   \n" +
            "\t\t\t\tand a.workcode = 2\n" +
            "\t\t\twhere (a.eventTime between :initDate and :endDate)\n" +
            "\t\t) attendancelogview ) extraTime\n" +
            "        \n" +
            "\t) AS query1\n" +
            "    group by month, date_range) query2\n" +
            "   where mes between :monthI and :monthF " +
            "group by mes, periodo", nativeQuery = true)
    List<Object> countEmployeeBonusTime(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("monthI") Integer monthI, @Param("monthF") Integer monthF);

    @Query(value = "select mes, periodo, SUM(total)\n" +
            "from (\n" +
            "select 1 mes, 1 periodo, 0 total union\n" +
            "select 1, 2, 0 union\n" +
            "select 2, 1, 0 union\n" +
            "select 2, 2, 0 union\n" +
            "select 3, 1, 0 union\n" +
            "select 3, 2, 0 union\n" +
            "select 4, 1, 0 union\n" +
            "select 4, 2, 0 union\n" +
            "select 5, 1, 0 union\n" +
            "select 5, 2, 0 union\n" +
            "select 6, 1, 0 union\n" +
            "select 6, 2, 0 union\n" +
            "select 7, 1, 0 union\n" +
            "select 7, 2, 0 union\n" +
            "select 8, 1, 0 union\n" +
            "select 8, 2, 0 union\n" +
            "select 9, 1, 0 union\n" +
            "select 9, 2, 0 union\n" +
            "select 10, 1, 0 union\n" +
            "select 10, 2, 0 union\n" +
            "select 11, 1, 0 union\n" +
            "select 11, 2, 0 union\n" +
            "select 12, 1, 0 union\n" +
            "select 12, 2, 0 union\n" +
            "SELECT\n" +
            "case " +
            "when day >= p1 " +
            "and day < p2 then month " +
            "when p1 = 1 " +
            "and day >= p2 then month " +
            "when p1 > 1 " +
            "and day < p1 then (month - 1) " +
            "else 1 " +
            "end, " +
            "case " +
            "when day >= p1 " +
            "and day < p2 then 1 " +
            "when p1 = 1 " +
            "and day >= p2 then 2 " +
            "when p1 > 1 " +
            "and day < p1 then 2 " +
            "else 1 " +
            "end as date_range, " +
            "        SUM(bonustime)\n" +
            "FROM\n" +
            "    (\n" +
            "    select \n" +
            "    YEAR(extraTime.eventtime) year,\n" +
            "\tMONTH(extraTime.eventtime) month,\n" +
            "\tDAY(extraTime.eventtime) day,\n" +
            "    extraTime.bonustime,\n" +
            "            (SELECT \n" +
            "                    value_setting\n" +
            "                FROM\n" +
            "                    system_setting\n" +
            "                WHERE\n" +
            "                    group_setting = 'ATTENDANCE'\n" +
            "                        AND key_setting = 'PeriodStart') p1,\n" +
            "            (SELECT \n" +
            "                    value_setting\n" +
            "                FROM\n" +
            "                    system_setting\n" +
            "                WHERE\n" +
            "                    group_setting = 'ATTENDANCE'\n" +
            "                        AND key_setting = 'PeriodEnd') p2\n" +
            "    from (select\n" +
            "\t\t\tid_attendancelog,\n" +
            "\t\t\tname,\n" +
            "\t\t\teventtime,\n" +
            "\t\t\tcheckout,\n" +
            "\t\t\touttime,\n" +
            "\t\t\ttolerance,\n" +
            "\t\t\tovertime > 0 approved,\n" +
            "\t\t\tconcat('', sec_to_time(outtime)) textOut, \n" +
            "\t\t\tconcat('', time(eventtime)) textOu,\n" +
            "\t\t\tconcat('', date(eventtime)) textDt, \n" +
            "\t\t\tround((checkout - outtime) / (60 * 60)) bonustime,\n" +
            "\t\t\tid_employee,\n" +
            "\t\t\tworkcode \n" +
            "\t\tfrom (\n" +
            "\t\t\tselect \n" +
            "\t\t\t\ta.id_attendancelog,\n" +
            "\t\t\t\tconcat(e.name, ' ', coalesce(concat(e.last_name_p,' '), ''), coalesce(concat('', e.last_name_m), '')) \"name\",\n" +
            "\t\t\t\ta.eventtime,\n" +
            "\t\t\t\ttime_to_sec(eventtime) checkout,\n" +
            "\t\t\t\tcoalesce((select sd.end from schedule_days sd where sd.day_id = weekday(a.eventtime) + 1 and sd.id_employee_schedule = e.id_employee_schedule), 23 * 60 * 60 + 59 * 60 + 59) \"outtime\",\n" +
            "\t\t\t\tcast(time_to_sec(coalesce((select value_setting from system_setting where group_setting = 'ATTENDANCE' and key_setting = 'ArrivalTolerance'), '00:00:00')) as UNSIGNED) tolerance,\n" +
            "\t\t\t\t(select count(*) > 0 from bonustime bt where bt.id_attendancelog = a.id_attendancelog) overtime,\n" +
            "\t\t\t\ta.id_employee,\n" +
            "\t\t\t\ta.workcode  \n" +
            "\t\t\tfrom attendancelog a   \n" +
            "\t\t\tinner join employee e   \n" +
            "\t\t\t\ton a.id_employee = e.id_employee   \n" +
            "\t\t\t\tand a.workcode = 2\n" +
            "\t\t\twhere e.id_district = :idDistrict\n" +
            "\t\t\tand (a.eventTime between :initDate and :endDate)\n" +
            "\t\t) attendancelogview ) extraTime\n" +
            "        \n" +
            "\t) AS query1\n" +
            "    group by month, date_range) query2\n" +
            "   where mes between :monthI and :monthF " +
            "group by mes, periodo", nativeQuery = true)
    List<Object> countEmployeeBonusTimeByDistrict(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("idDistrict") Long idDistrict, @Param("monthI") Integer monthI, @Param("monthF") Integer monthF);

    @Query(value = "select mes, periodo, SUM(total)\n" +
            "from (\n" +
            "select 1 mes, 1 periodo, 0 total union\n" +
            "select 1, 2, 0 union\n" +
            "select 2, 1, 0 union\n" +
            "select 2, 2, 0 union\n" +
            "select 3, 1, 0 union\n" +
            "select 3, 2, 0 union\n" +
            "select 4, 1, 0 union\n" +
            "select 4, 2, 0 union\n" +
            "select 5, 1, 0 union\n" +
            "select 5, 2, 0 union\n" +
            "select 6, 1, 0 union\n" +
            "select 6, 2, 0 union\n" +
            "select 7, 1, 0 union\n" +
            "select 7, 2, 0 union\n" +
            "select 8, 1, 0 union\n" +
            "select 8, 2, 0 union\n" +
            "select 9, 1, 0 union\n" +
            "select 9, 2, 0 union\n" +
            "select 10, 1, 0 union\n" +
            "select 10, 2, 0 union\n" +
            "select 11, 1, 0 union\n" +
            "select 11, 2, 0 union\n" +
            "select 12, 1, 0 union\n" +
            "select 12, 2, 0 union\n" +
            "SELECT\n" +
            "case " +
            "when day >= p1 " +
            "and day < p2 then month " +
            "when p1 = 1 " +
            "and day >= p2 then month " +
            "when p1 > 1 " +
            "and day < p1 then (month - 1) " +
            "else 1 " +
            "end, " +
            "case " +
            "when day >= p1 " +
            "and day < p2 then 1 " +
            "when p1 = 1 " +
            "and day >= p2 then 2 " +
            "when p1 > 1 " +
            "and day < p1 then 2 " +
            "else 1 " +
            "end as date_range, " +
            "        SUM(bonustime)\n" +
            "FROM\n" +
            "    (\n" +
            "    select \n" +
            "    YEAR(extraTime.eventtime) year,\n" +
            "\tMONTH(extraTime.eventtime) month,\n" +
            "\tDAY(extraTime.eventtime) day,\n" +
            "    extraTime.bonustime,\n" +
            "            (SELECT \n" +
            "                    value_setting\n" +
            "                FROM\n" +
            "                    system_setting\n" +
            "                WHERE\n" +
            "                    group_setting = 'ATTENDANCE'\n" +
            "                        AND key_setting = 'PeriodStart') p1,\n" +
            "            (SELECT \n" +
            "                    value_setting\n" +
            "                FROM\n" +
            "                    system_setting\n" +
            "                WHERE\n" +
            "                    group_setting = 'ATTENDANCE'\n" +
            "                        AND key_setting = 'PeriodEnd') p2\n" +
            "    from (select\n" +
            "\t\t\tid_attendancelog,\n" +
            "\t\t\tname,\n" +
            "\t\t\teventtime,\n" +
            "\t\t\tcheckout,\n" +
            "\t\t\touttime,\n" +
            "\t\t\ttolerance,\n" +
            "\t\t\tovertime > 0 approved,\n" +
            "\t\t\tconcat('', sec_to_time(outtime)) textOut, \n" +
            "\t\t\tconcat('', time(eventtime)) textOu,\n" +
            "\t\t\tconcat('', date(eventtime)) textDt, \n" +
            "\t\t\tround((checkout - outtime) / (60 * 60)) bonustime,\n" +
            "\t\t\tid_employee,\n" +
            "\t\t\tworkcode \n" +
            "\t\tfrom (\n" +
            "\t\t\tselect \n" +
            "\t\t\t\ta.id_attendancelog,\n" +
            "\t\t\t\tconcat(e.name, ' ', coalesce(concat(e.last_name_p,' '), ''), coalesce(concat('', e.last_name_m), '')) \"name\",\n" +
            "\t\t\t\ta.eventtime,\n" +
            "\t\t\t\ttime_to_sec(eventtime) checkout,\n" +
            "\t\t\t\tcoalesce((select sd.end from schedule_days sd where sd.day_id = weekday(a.eventtime) + 1 and sd.id_employee_schedule = e.id_employee_schedule), 23 * 60 * 60 + 59 * 60 + 59) \"outtime\",\n" +
            "\t\t\t\tcast(time_to_sec(coalesce((select value_setting from system_setting where group_setting = 'ATTENDANCE' and key_setting = 'ArrivalTolerance'), '00:00:00')) as UNSIGNED) tolerance,\n" +
            "\t\t\t\t(select count(*) > 0 from bonustime bt where bt.id_attendancelog = a.id_attendancelog) overtime,\n" +
            "\t\t\t\ta.id_employee,\n" +
            "\t\t\t\ta.workcode  \n" +
            "\t\t\tfrom attendancelog a   \n" +
            "\t\t\tinner join employee e   \n" +
            "\t\t\t\ton a.id_employee = e.id_employee   \n" +
            "\t\t\t\tand a.workcode = 2\n" +
            "\t\t\twhere e.id_employee = :idEmployee\n" +
            "\t\t\tand (a.eventTime between :initDate and :endDate)\n" +
            "\t\t) attendancelogview ) extraTime\n" +
            "        \n" +
            "\t) AS query1\n" +
            "    group by month, date_range) query2\n" +
            "   where mes between :monthI and :monthF " +
            "group by mes, periodo", nativeQuery = true)
    List<Object> countEmployeeBonusTimeByOperator(@Param("initDate") Calendar initDate, @Param("endDate") Calendar endDate, @Param("idEmployee") Long idEmployee, @Param("monthI") Integer monthI, @Param("monthF") Integer monthF);


}