package com.umeca.repository.supervisorManager;

import com.umeca.model.catalog.StatisticSupervisorManagerReportType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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


    @Query(value = "SELECT count(assigned_arrangement.id_arrangement), cat_arrangement.description FROM cat_arrangement " +
            "left join assigned_arrangement " +
            "on assigned_arrangement.id_arrangement = cat_arrangement.id_arrangement " +
            "left join hearing_format " +
            "on hearing_format.id_hearing_format = assigned_arrangement.id_hearing_format " +
            "left join cat_district " +
            "on hearing_format.id_district = cat_district.id_district " +
            "where hearing_format.is_finished = true or hearing_format.is_finished is null " +
            "group by  cat_arrangement.id_arrangement", nativeQuery = true)
    List<Object> getCountCasesByArrangement();


    @Query(value = "SELECT count(assigned_arrangement.id_arrangement), cat_arrangement.description FROM cat_arrangement " +
            "inner join assigned_arrangement " +
            "on assigned_arrangement.id_arrangement = cat_arrangement.id_arrangement " +
            "inner join hearing_format " +
            "on hearing_format.id_hearing_format = assigned_arrangement.id_hearing_format " +
            "inner join cat_district " +
            "on hearing_format.id_district = cat_district.id_district " +
            "where hearing_format.is_finished = true or hearing_format.is_finished is null " +
            "group by  cat_arrangement.id_arrangement ", nativeQuery = true)
    List<Object> getCountCasesByArrangementOnlyAssigned();


    @Query(value = "SELECT count(assigned_arrangement.id_arrangement), cat_arrangement.description FROM cat_arrangement " +
            "inner join assigned_arrangement " +
            "on assigned_arrangement.id_arrangement = cat_arrangement.id_arrangement " +
            "inner join hearing_format " +
            "on hearing_format.id_hearing_format = assigned_arrangement.id_hearing_format " +
            "inner join cat_district " +
            "on hearing_format.id_district = cat_district.id_district " +
            "where (hearing_format.id_district = :idDistrict) and (hearing_format.is_finished = true or hearing_format.is_finished is null) " +
            "group by  cat_arrangement.id_arrangement", nativeQuery = true)
    List<Object> getCountCasesByArrangementAndDistrict(@Param("idDistrict") Long idDistrict);


    @Query(value = "select cat_arrangement.description ,count(assigned_arrangement.id_arrangement) from assigned_arrangement " +
            "inner join cat_arrangement " +
            "on assigned_arrangement.id_arrangement = cat_arrangement.id_arrangement " +
            "inner join hearing_format " +
            "on assigned_arrangement.id_hearing_format = hearing_format.id_hearing_format " +
            "where hearing_format.id_user = :supervisorId " +
            "group by assigned_arrangement.id_arrangement", nativeQuery = true)
    List<Object> getArrangementBySupervisorId(@Param("supervisorId") Long supervisorId);


    @Query(value = "select cat_arrangement.description ,count(assigned_arrangement.id_arrangement) from assigned_arrangement " +
            "inner join cat_arrangement " +
            "on assigned_arrangement.id_arrangement = cat_arrangement.id_arrangement " +
            "inner join hearing_format " +
            "on assigned_arrangement.id_hearing_format = hearing_format.id_hearing_format " +
            "where hearing_format.id_user = :supervisorId and assigned_arrangement.id_arrangement = :arrangementId and hearing_format.id_district = :districtId " +
            "group by assigned_arrangement.id_arrangement",  nativeQuery = true)
    List<Object> getArrangementByIdAndSupervisorId(@Param("supervisorId") Long supervisorId, @Param("arrangementId") Long arrangementId, @Param("districtId") Long districtId);




    //Sustancias general
    @Query(value = "select drug, count(idDrug) " +
            "from (select distinct FM.id_framing_meeting as idFr, DR.id_drug_type as idDrug, DRTY.drug as drug " +
            "from case_detention CA " +
            "inner join framing_meeting FM on CA.id_case=FM.id_case " +
            "inner join drug DR on FM.id_framing_meeting=DR.id_framing_meeting " +
            "inner join cat_drug_type DRTY on DR.id_drug_type=DRTY.id_drug_type " +
            "where DRTY.id_drug_type<>15 " +
            "and FM.end_date between :initDate and :endDate " +
            "and FM.is_terminated = true) as t1 " +
            "group by drug " +
            "order by drug ", nativeQuery = true)
    List<Object> countTypeofDrugs(@Param("initDate") Date initDate, @Param("endDate") Date endDate);


    //Sustancias por distrito
    @Query(value = "select drug, count(idDrug) " +
            "from (select distinct FM.id_framing_meeting as idFr, DR.id_drug_type as idDrug, DRTY.drug as drug " +
            "from case_detention CA " +
            "inner join framing_meeting FM on CA.id_case=FM.id_case " +
            "inner join drug DR on FM.id_framing_meeting=DR.id_framing_meeting " +
            "inner join cat_drug_type DRTY on DR.id_drug_type=DRTY.id_drug_type " +
            "inner join cat_district DIST on CA.id_district=DIST.id_district " +
            "where DRTY.id_drug_type<>15 " +
            "and FM.end_date between :initDate and :endDate " +
            "and DIST.id_district = :districtId " +
            "and FM.is_terminated = true) as t1 " +
            "group by drug " +
            "order by drug ", nativeQuery = true)
    List<Object> countTypeofDrugsByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);


    //Sustancias por distrito Y operador
    @Query(value = " select idDrug, drug, count(idDrug), user " +
            "from (select distinct FM.id_framing_meeting as idFr, DR.id_drug_type as idDrug, DRTY.drug as drug, USR.fullname as user " +
            "from case_detention CA " +
            "inner join framing_meeting FM on CA.id_case=FM.id_case " +
            "inner join drug DR on FM.id_framing_meeting = DR.id_framing_meeting " +
            "inner join cat_drug_type DRTY on DR.id_drug_type = DRTY.id_drug_type " +
            "inner join cat_district DIST on CA.id_district = DIST.id_district " +
            "inner join user USR on FM.id_user = USR.id_user " +
            "where DRTY.id_drug_type<>15 " +
            "and FM.id_user = :supervisorId " +
            "and FM.end_date between :initDate and :endDate " +
            "and DIST.id_district = :districtId " +
            "and FM.is_terminated = true) as t1 " +
            "group by drug " +
            "order by idDrug ", nativeQuery = true)
    List<Object> countTypeofDrugsByDistrictAndSupervisor(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId, @Param("supervisorId") Long supervisorId);




}
