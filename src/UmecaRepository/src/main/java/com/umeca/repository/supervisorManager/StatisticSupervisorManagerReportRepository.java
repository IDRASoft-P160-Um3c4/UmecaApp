package com.umeca.repository.supervisorManager;

import com.umeca.model.catalog.StatisticSupervisorManagerReportType;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.shared.SelectList;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sun.rmi.transport.ObjectTable;

import java.util.Date;
import java.util.List;

/**
 * Created by DeveloperII on 04/09/2015.
 */
@Repository("qStatisticSupervisorManagerReportRepository")
public interface StatisticSupervisorManagerReportRepository extends JpaRepository<StatisticSupervisorManagerReportType, Long> {


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
            "where (hearing_format.id_hearing_format in (select max(hearing_format.id_hearing_format) from hearing_format " +
            "inner join case_detention " +
            "on hearing_format.id_case = case_detention.id_case " +
            "where hearing_format.register_timestamp between :initDate and :endDate " +
            "group by hearing_format.id_case) or hearing_format.id_hearing_format is null) and (hearing_format.is_finished = true or hearing_format.is_finished is null) " +
            "group by  cat_arrangement.id_arrangement", nativeQuery = true)
    List<Object> getCountCasesByArrangement(@Param("initDate") String initDate, @Param("endDate") String endDate);


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
            "left join assigned_arrangement " +
            "on assigned_arrangement.id_arrangement = cat_arrangement.id_arrangement " +
            "left join hearing_format " +
            "on hearing_format.id_hearing_format = assigned_arrangement.id_hearing_format " +
            "left join cat_district " +
            "on hearing_format.id_district = cat_district.id_district " +
            "where (hearing_format.id_hearing_format in (select max(hearing_format.id_hearing_format) from hearing_format " +
            "inner join case_detention " +
            "on hearing_format.id_case = case_detention.id_case " +
            "where hearing_format.register_timestamp between :initDate and :endDate and hearing_format.id_district = :idDistrict " +
            "group by hearing_format.id_case) or hearing_format.id_hearing_format is null) and (hearing_format.is_finished = true or hearing_format.is_finished is null) " +
            "group by  cat_arrangement.id_arrangement", nativeQuery = true)
    List<Object> getCountCasesByArrangementAndDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict);


    @Query(value = "select cat_arrangement.description ,count(assigned_arrangement.id_arrangement) from assigned_arrangement " +
            "inner join cat_arrangement " +
            "on assigned_arrangement.id_arrangement = cat_arrangement.id_arrangement " +
            "inner join hearing_format " +
            "on assigned_arrangement.id_hearing_format = hearing_format.id_hearing_format " +
            "where hearing_format.id_user = :supervisorId " +
            "group by assigned_arrangement.id_arrangement", nativeQuery = true)
    List<Object> getArrangementBySupervisorId(@Param("supervisorId") Long supervisorId);


    @Query(value = "select " +
            "cat_arrangement.description, " +
            "count(ResA.caseHF) " +
            "from assigned_arrangement " +
            "inner join( " +
            "select " +
            "case_detention.id_case 'caseHF', " +
            "max(hearing_format.id_hearing_format) 'lastHF' " +
            "from case_detention " +
            "inner join  hearing_format " +
            "on hearing_format.id_case = case_detention.id_case and hearing_format.is_finished = true and case_detention.id_umeca_supervisor = :supervisorId and case_detention.id_district = :districtId " +
            "and hearing_format.register_timestamp between :initDate and :endDate " +
            "inner join cat_status_case " +
            "on cat_status_case.id_status = case_detention.id_status and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
            "group by case_detention.id_case)ResA " +
            "on assigned_arrangement.id_hearing_format = ResA.lastHF " +
            "right join cat_arrangement " +
            "on assigned_arrangement.id_arrangement = cat_arrangement.id_arrangement " +
            "group by cat_arrangement.id_arrangement ", nativeQuery = true)
    List<Object> getArrangementByIdAndSupervisorId(@Param("initDate") String initDate, @Param("endDate") String endDate,  @Param("districtId") Long districtId, @Param("supervisorId") Long supervisorId);

        @Query(value = "select " +
                "cat_arrangement.description, " +
                "count(ResA.caseHF) " +
                "from assigned_arrangement " +
                "inner join( " +
                "select " +
                "case_detention.id_case 'caseHF', " +
                "max(hearing_format.id_hearing_format) 'lastHF' " +
                "from case_detention " +
                "inner join  hearing_format " +
                "on hearing_format.id_case = case_detention.id_case and hearing_format.is_finished = true and case_detention.id_umeca_supervisor is null and case_detention.id_district = :districtId " +
                "and hearing_format.register_timestamp between :initDate and :endDate " +
                "inner join cat_status_case " +
                "on cat_status_case.id_status = case_detention.id_status and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "group by case_detention.id_case)ResA " +
                "on assigned_arrangement.id_hearing_format = ResA.lastHF " +
                "right join cat_arrangement " +
                "on assigned_arrangement.id_arrangement = cat_arrangement.id_arrangement " +
                "group by cat_arrangement.id_arrangement ", nativeQuery = true)
        List<Object> getArrangementByNotAssignetSupervisor(@Param("initDate") String initDate, @Param("endDate") String endDate,  @Param("districtId") Long districtId);

    @Query(value = "select  framing_imputed_personal_data.gender, count(distinct(framing_meeting.id_framing_meeting)) from framing_meeting " +
            "inner join framing_imputed_personal_data " +
            "on framing_meeting.id_framing_imputed_personal_data = framing_imputed_personal_data.id_framing_imputed_personal_data " +
            "inner join drug " +
            "on framing_meeting.id_framing_meeting = drug.id_framing_meeting " +
            "where drug.id_drug_type <> 15 and framing_meeting.is_terminated = true and (framing_meeting.end_date between :initDate and :endDate ) " +
            "group by framing_imputed_personal_data.gender", nativeQuery = true)
    List<Object> getNumberOfPeopleByGenderWhoUseDrugsGeneral(@Param("initDate") String initDate, @Param("endDate") String endDate);

    @Query(value = "select  framing_imputed_personal_data.gender, count(distinct(framing_meeting.id_framing_meeting)) from framing_meeting " +
            "inner join framing_imputed_personal_data " +
            "on framing_meeting.id_framing_imputed_personal_data = framing_imputed_personal_data.id_framing_imputed_personal_data " +
            "inner join drug " +
            "on framing_meeting.id_framing_meeting = drug.id_framing_meeting " +
            "inner join case_detention " +
            "on framing_meeting.id_case = case_detention.id_case " +
            "where drug.id_drug_type <> 15 and framing_meeting.is_terminated = true and (framing_meeting.end_date between :initDate and :endDate ) and case_detention.id_district = :idDistrict " +
            "group by framing_imputed_personal_data.gender", nativeQuery = true)
    List<Object> getNumberOfPeopleByGenderWhoUseDrugsByDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict);


    @Query(value = "select  framing_imputed_personal_data.gender, count(distinct(framing_meeting.id_framing_meeting)) from framing_meeting " +
            "inner join framing_imputed_personal_data " +
            "on framing_meeting.id_framing_imputed_personal_data = framing_imputed_personal_data.id_framing_imputed_personal_data " +
            "inner join drug " +
            "on framing_meeting.id_framing_meeting = drug.id_framing_meeting " +
            "inner join case_detention " +
            "on framing_meeting.id_case = case_detention.id_case " +
            "where drug.id_drug_type <> 15 and framing_meeting.is_terminated = true and (framing_meeting.end_date between :initDate and :endDate ) and case_detention.id_district = :idDistrict and " +
            "framing_meeting.id_user = :idSupervisor " +
            "group by framing_imputed_personal_data.gender", nativeQuery = true)
    List<Object> getNumberOfPeopleByGenderWhoUseDrugsByDistrictAndSupervisor(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict, @Param("idSupervisor") Long idSupervisor);


    @Query(value = "select " +
            "SUM(if(hasChanneling = 0, 1, 0)) AS 'NotChanneling', " +
            "SUM(if(hasChanneling = 1, 1, 0)) AS 'Channeling' " +
            "FROM( " +
            "select " +
            "distinct " +
            "    case_detention.id_case, " +
            "    if(channeling.id_channeling IS NULL, 0, 1) AS 'hasChanneling' " +
            "from case_detention " +
            "inner join hearing_format " +
            "on hearing_format.id_case = case_detention.id_case and hearing_format.is_finished = true " +
            "inner join cat_status_case " +
            "on cat_status_case.id_status = case_detention.id_status and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
            "left join channeling on case_detention.id_case = channeling.id_case " +
            "where channeling.creation_date between :initDate and :endDate or channeling.creation_date is null ) ResA", nativeQuery = true)
    List<Object> getNumberCasesWithChannelingGeneral(@Param("initDate") String initDate, @Param("endDate") String endDate);


    @Query(value = "select " +
            "SUM(if(hasChanneling = 0, 1, 0)) AS 'NotChanneling'," +
            "SUM(if(hasChanneling = 1, 1, 0)) AS 'Channeling' " +
            "FROM( " +
            "select " +
            "distinct " +
            "    case_detention.id_case, " +
            "    if(channeling.id_channeling IS NULL, 0, 1) AS 'hasChanneling' " +
            "from case_detention  " +
            "inner join hearing_format " +
            "on hearing_format.id_case = case_detention.id_case and hearing_format.is_finished = true " +
            "inner join cat_status_case " +
            "on cat_status_case.id_status = case_detention.id_status and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
            "left join channeling on case_detention.id_case = channeling.id_case " +
            "where hearing_format.id_district = :idDistrict and (channeling.creation_date between :initDate and :endDate) or channeling.creation_date is null ) ResA", nativeQuery = true)
    List<Object> getNumberCasesWithChannelingByDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict);


    @Query(value = "select \n" +
            "SUM(if(hasChanneling = 0, 1, 0)) AS 'NotChanneling', " +
            "SUM(if(hasChanneling = 1, 1, 0)) AS 'Channeling' " +
            "FROM( " +
            "select " +
            "distinct " +
            "    case_detention.id_case, " +
            "    if(channeling.id_channeling IS NULL, 0, 1) AS 'hasChanneling' " +
            "from  case_detention " +
            "inner join hearing_format " +
            "on hearing_format.id_case = case_detention.id_case and hearing_format.is_finished = true " +
            "inner join cat_status_case " +
            "on cat_status_case.id_status = case_detention.id_status and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
            "left join channeling on case_detention.id_case = channeling.id_case " +
            "where hearing_format.id_district = :idDistrict and channeling.id_creator_user = :idSupervisor " +
            "and (channeling.creation_date between :initDate and :endDate)) ResA", nativeQuery = true)
    List<Object> getNumberCasesWithChannelingByDistrictAndOperator(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict, @Param("idSupervisor") Long idSupervisor);


    //Sustancias general
    @Query(value = "select idDrug, drug, count(idDrug) " +
            "from (select distinct FM.id_framing_meeting as idFr, DR.id_drug_type as idDrug, DRTY.drug as drug " +
            "from case_detention CA " +
            "inner join framing_meeting FM on CA.id_case=FM.id_case " +
            "inner join drug DR on FM.id_framing_meeting=DR.id_framing_meeting " +
            "inner join cat_drug_type DRTY on DR.id_drug_type=DRTY.id_drug_type " +
            "where DRTY.id_drug_type<>15 " +
            "and FM.end_date between :initDate and :endDate " +
            "and FM.is_terminated = true) as t1 " +
            "group by drug " +
            "order by idDrug ", nativeQuery = true)
    List<Object> countTypeofDrugs(@Param("initDate") Date initDate, @Param("endDate") Date endDate);


    //Sustancias por distrito
    @Query(value = "select idDrug, drug, count(idDrug) " +
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
            "order by idDrug ", nativeQuery = true)
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


    @Query(value = "select cat_channeling_institution_name.name , count(cat_channeling_institution_name.id_cat_channeling_institution_name) from channeling " +
            "inner join cat_channeling_institution_name " +
            "on channeling.id_cat_institution_name = cat_channeling_institution_name.id_cat_channeling_institution_name " +
            "where channeling.creation_date  between :initDate and :endDate " +
            "group by cat_channeling_institution_name.id_cat_channeling_institution_name", nativeQuery = true)
    List<Object> countInstitutionChannelingGeneral(@Param("initDate") String initDate, @Param("endDate") String endDate);

    @Query(value = "select cat_channeling_institution_name.name , count(cat_channeling_institution_name.id_cat_channeling_institution_name) from channeling " +
            "inner join cat_channeling_institution_name " +
            "on channeling.id_cat_institution_name = cat_channeling_institution_name.id_cat_channeling_institution_name " +
            "where channeling.creation_date  between :initDate and :endDate and channeling.id_district = :idDistrict " +
            "group by cat_channeling_institution_name.id_cat_channeling_institution_name", nativeQuery = true)
    List<Object> countInstitutionChannelingByDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict);


    @Query(value = "select cat_channeling_institution_name.name, count(channeling.id_cat_institution_name) from cat_channeling_institution_name " +
            "left join channeling " +
            "on channeling.id_cat_institution_name = cat_channeling_institution_name.id_cat_channeling_institution_name and channeling.id_creator_user = :idSupervisor and channeling.id_district = :idDistrict and (channeling.creation_date between :initDate and :endDate) " +
            "group by cat_channeling_institution_name.id_cat_channeling_institution_name", nativeQuery = true)
    List<Object> countInstitutionChannelingBySupervisor(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict, @Param("idSupervisor") Long idSupervisor);

    //empleos general
    @Query("select new com.umeca.model.shared.SelectList(" +
            "case when caframejob.company  <> 'NO TRABAJA' then 'Empleados' " +
            "else 'Sin empleo' end, count(ca.id)) " +
            "from Case ca " +
            "inner join ca.framingMeeting caframe " +
            "inner join caframe.jobs caframejob " +
            "where caframe.endDate between :initDate and :endDate " +
            "group by case when caframejob.company <> 'NO TRABAJA' then 'Empleados' else 'Sin empleo' end")
    List<SelectList> countJobs(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

    //empleos por distrito
    @Query("select new com.umeca.model.shared.SelectList(" +
            "case when caframejob.company  <> 'NO TRABAJA' then 'Empleados' " +
            "else 'Sin empleo' end, count(ca.id)) " +
            "from Case ca " +
            "inner join ca.framingMeeting caframe " +
            "inner join caframe.jobs caframejob " +
            "where caframe.endDate between :initDate and :endDate " +
            "and ca.district.id = :districtId " +
            "group by case when caframejob.company <> 'NO TRABAJA' then 'Empleados' else 'Sin empleo' end")
    List<SelectList> countJobsByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);


    //empleos por distrito y operador
    @Query("select new com.umeca.model.shared.SelectList(" +
            "case when caframejob.company  <> 'NO TRABAJA' then 'Empleados' " +
            "else 'Sin empleo' end, count(ca.id)) " +
            "from Case ca " +
            "inner join ca.framingMeeting caframe " +
            "inner join caframe.jobs caframejob " +
            "where caframe.endDate between :initDate and :endDate " +
            "and ca.district.id = :districtId " +
            "and caframe.supervisor.id = :supervisorId " +
            "group by case when caframejob.company <> 'NO TRABAJA' then 'Empleados' else 'Sin empleo' end")
    List<SelectList> countJobsByDistrictAndSupervisor(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId, @Param("supervisorId") Long supervisorId);


    //casos cerrados general
    @Query("select new com.umeca.model.shared.SelectList(" +
            "case when ca.status.name  = 'ST_CASE_CLOSED' then 'Cerrados' " +
            "else 'Vigentes' end, count(distinct ca.id)) " +
            "from Case ca " +
            "inner join ca.hearingFormats cahear " +
            "where ca.dateCreate between :initDate and :endDate " +
            "and cahear.isFinished = true " +
            "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST', 'ST_CASE_CLOSED') " +
            "group by case when ca.status.id = 10 then 'Cerrados' else 'Vigentes' end")
    List<SelectList> countClosedCases(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

    //casos cerrados por distrito
    @Query("select new com.umeca.model.shared.SelectList(" +
            "case when ca.status.name  = 'ST_CASE_CLOSED' then 'Cerrados' " +
            "else 'Vigentes' end, count(distinct ca.id)) " +
            "from Case ca " +
            "inner join ca.hearingFormats cahear " +
            "where ca.dateCreate between :initDate and :endDate " +
            "and cahear.isFinished = true " +
            "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST', 'ST_CASE_CLOSED') " +
            "and ca.district.id = :districtId " +
            "group by case when ca.status.name  = 'ST_CASE_CLOSED' then 'Cerrados' else 'Vigentes' end")
    List<SelectList> countClosedCasesByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);


    //casos cerrados por distrito y operador
    @Query("select new com.umeca.model.shared.SelectList(" +
            "case when ca.status.name  = 'ST_CASE_CLOSED' then 'Cerrados' " +
            "else 'Vigentes' end, count(distinct ca.id)) " +
            "from Case ca " +
            "inner join ca.hearingFormats cahear " +
            "where ca.dateCreate between :initDate and :endDate " +
            "and cahear.isFinished = true " +
            "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST', 'ST_CASE_CLOSED') " +
            "and ca.district.id = :districtId " +
            "and ca.umecaSupervisor.id = :supervisorId " +
            "group by case when ca.status.name  = 'ST_CASE_CLOSED' then 'Cerrados' else 'Vigentes' end")
    List<SelectList> countClosedCasesByDistrictAndSupervisor(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId, @Param("supervisorId") Long supervisorId);

    //casos cerrados sin supervisor
    @Query("select new com.umeca.model.shared.SelectList(" +
            "case when ca.status.name  = 'ST_CASE_CLOSED' then 'Cerrados' " +
            "else 'Vigentes' end, count(distinct ca.id)) " +
            "from Case ca " +
            "inner join ca.hearingFormats cahear " +
            "where ca.dateCreate between :initDate and :endDate " +
            "and cahear.isFinished = true " +
            "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST', 'ST_CASE_CLOSED') " +
            "and ca.district.id = :districtId " +
            "and ca.umecaSupervisor = null " +
            "group by case when ca.status.name  = 'ST_CASE_CLOSED' then 'Cerrados' else 'Vigentes' end")
    List<SelectList> countClosedCasesByDistrictAndSupervisorNull(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);


    @Query("select new com.umeca.model.shared.SelectList(clC.name,count(clC.id)) " +
            "from Case c " +
            "inner join c.closeCause clC " +
            "inner join c.status cStus " +
            "where (c.closeDate between :initDate and :endDate) and cStus.name = 'ST_CASE_CLOSED' " +
            "group by clC.name")
    List<SelectList> countClosedCasesTypeGeneral(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

    @Query("select new com.umeca.model.shared.SelectList(clC.name,count(clC.id)) " +
            "from Case c " +
            "inner join c.closeCause clC " +
            "inner join c.status cStus " +
            "where (c.closeDate between :initDate and :endDate) and cStus.name = 'ST_CASE_CLOSED' " +
            "and c.district.id = :districtId " +
            "group by clC.name")
    List<SelectList> countClosedCasesTypeByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long idDistrict);

    @Query(value = "select close_cause.name, count(case_detention.id_close_cause) from close_cause " +
            "left join case_detention " +
            "on case_detention.id_close_cause = close_cause.id_close_cause and (case_detention.close_date between :initDate and :endDate) and case_detention.closer_user = :supervisorId and  case_detention.id_district = :idDistrict " +
            "left join cat_status_case " +
            "on case_detention.id_status = cat_status_case.id_status and cat_status_case.status = 'ST_CASE_CLOSED' " +
            "group by close_cause.id_close_cause", nativeQuery = true)
    List<Object> countClosedCasesTypeByOperator(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict, @Param("supervisorId") Long supervisorId);


    //medida cautelar general
    @Query("select new com.umeca.model.shared.SelectList(" +
            "case when cahear.hearingFormatSpecs.arrangementType  = 1 then 'Medida cautelar' else 'Medida cautelar' end, count(distinct ca.id)) " +
            "from Case ca " +
            "inner join ca.hearingFormats cahear " +
            "where ca.dateCreate between :initDate and :endDate " +
            "and cahear.isFinished = true " +
            "and cahear.hearingFormatSpecs.arrangementType = 1 " +
            "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
            "order by cahear.id desc")
    List<SelectList> countWarningMeasure(@Param("initDate") Date initDate, @Param("endDate") Date endDate);


    //medida cautelar por distrito
    @Query("select new com.umeca.model.shared.SelectList(" +
            "case when cahear.hearingFormatSpecs.arrangementType  = 1 then 'Medida cautelar' else 'Medida cautelar' end, count(distinct ca.id)) " +
            "from Case ca " +
            "inner join ca.hearingFormats cahear " +
            "where ca.dateCreate between :initDate and :endDate " +
            "and cahear.isFinished = true " +
            "and cahear.hearingFormatSpecs.arrangementType = 1 " +
            "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
            "and ca.district.id = :districtId " +
            "order by cahear.id desc")
    List<SelectList> countWarningMeasureByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);

    //medida cautelar por distrito y operador
    @Query("select new com.umeca.model.shared.SelectList(" +
            "case when cahear.hearingFormatSpecs.arrangementType  = 1 then 'Medida cautelar' else 'Medida cautelar' end, count(distinct ca.id)) " +
            "from Case ca " +
            "inner join ca.hearingFormats cahear " +
            "where ca.dateCreate between :initDate and :endDate " +
            "and cahear.isFinished = true " +
            "and cahear.hearingFormatSpecs.arrangementType = 1 " +
            "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
            "and ca.district.id = :districtId " +
            "and ca.umecaSupervisor.id = :supervisorId " +
            "order by cahear.id desc")
    List<SelectList> countWarningMeasureByDistrictAndSupervisor(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId, @Param("supervisorId") Long supervisorId);


    //medida cautelar sin supervisor
    @Query("select new com.umeca.model.shared.SelectList(" +
            "case when cahear.hearingFormatSpecs.arrangementType  = 1 then 'Medida cautelar' else 'Medida cautelar' end, count(distinct ca.id)) " +
            "from Case ca " +
            "inner join ca.hearingFormats cahear " +
            "where ca.dateCreate between :initDate and :endDate " +
            "and cahear.isFinished = true " +
            "and cahear.hearingFormatSpecs.arrangementType = 1 " +
            "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
            "and ca.district.id = :districtId " +
            "and ca.umecaSupervisor = null " +
            "order by cahear.id desc")
    List<SelectList> countWarningMeasureByDistrictAndSupervisorNull(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);


        @Query("select  new com.umeca.model.shared.SelectList(" +
                "case when evT.name  = 'MC_NON_FULFILLMENT'  then 'Medidas cautelares' when  evT.name  = 'MC_PARTIAL_NON_FULFILLMENT' then  'Medidas cautelares' else 'Medidas cautelares' end, count(distinct ev.caseDetention.id)) " +
                "from Event ev " +
                "inner join ev.eventType evT " +
                "where (ev.dateId between :initDate and :endDate) " +
                "and evT.name in ('MC_NON_FULFILLMENT','MC_PARTIAL_NON_FULFILLMENT')")
        List<SelectList> countMCWithNonFulfillmentGeneral(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);

        @Query("select  new com.umeca.model.shared.SelectList(" +
                "case when evT.name  = 'MC_NON_FULFILLMENT'  then 'Medidas cautelares' when  evT.name  = 'MC_PARTIAL_NON_FULFILLMENT' then  'Medidas cautelares' else 'Medidas cautelares' end, count(distinct ev.caseDetention.id)) " +
                "from Event ev " +
                "inner join ev.eventType evT " +
                "inner join ev.caseDetention c " +
                "where (ev.dateId between :initDate and :endDate) " +
                "and evT.name in ('MC_NON_FULFILLMENT','MC_PARTIAL_NON_FULFILLMENT') " +
                "and c.district.id = :districtId")
        List<SelectList> countMCWithNonFulfillByDistrict(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate, @Param("districtId") Long districtId);


        @Query("select  new com.umeca.model.shared.SelectList(" +
                "case when evT.name  = 'MC_NON_FULFILLMENT'  then 'Medidas cautelares' when  evT.name  = 'MC_PARTIAL_NON_FULFILLMENT' then  'Medidas cautelares' else 'Medidas cautelares' end, count(distinct ev.caseDetention.id)) " +
                "from Event ev " +
                "inner join ev.eventType evT " +
                "inner join ev.caseDetention c " +
                "inner join ev.caseDetention.monitoringPlan mP " +
                "where (ev.dateId between :initDate and :endDate) " +
                "and evT.name in ('MC_NON_FULFILLMENT','MC_PARTIAL_NON_FULFILLMENT') " +
                "and c.district.id = :districtId " +
                "and mP.supervisor.id = :supervisorId")
        List<SelectList> countMCWithNonFulfillBySupervisor(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate, @Param("districtId") Long districtId, @Param("supervisorId") Long supervisorId);


        @Query("select  new com.umeca.model.shared.SelectList(" +
                "case when evT.name  = 'SCP_PARTIAL_NON_FULFILLMENT'  then 'Suspensión Condicional' when  evT.name  = 'SCP_NON_FULFILLMENT' then  'Suspensión Condicional' else 'Suspensión Condicional' end, count(distinct ev.caseDetention.id)) " +
                "from Event ev " +
                "inner join ev.eventType evT " +
                "where (ev.dateId between :initDate and :endDate) " +
                "and evT.name in ('SCP_PARTIAL_NON_FULFILLMENT','SCP_NON_FULFILLMENT')")
        List<SelectList> countSCPPWithNonFulfillmentGeneral(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate);

        @Query("select  new com.umeca.model.shared.SelectList(" +
                "case when evT.name  = 'SCP_PARTIAL_NON_FULFILLMENT'  then 'Suspensión Condicional' when  evT.name  = 'SCP_NON_FULFILLMENT' then  'Suspensión Condicional' else 'Suspensión Condicional' end, count(distinct ev.caseDetention.id)) " +
                "from Event ev " +
                "inner join ev.eventType evT " +
                "inner join ev.caseDetention c " +
                "where (ev.dateId between :initDate and :endDate) " +
                "and evT.name in ('SCP_PARTIAL_NON_FULFILLMENT','SCP_NON_FULFILLMENT') " +
                "and c.district.id = :districtId")
        List<SelectList> countSCPPWithNonFulfillByDistrict(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate, @Param("districtId") Long districtId);


        @Query("select  new com.umeca.model.shared.SelectList(" +
                "case when evT.name  = 'SCP_PARTIAL_NON_FULFILLMENT'  then 'Suspensión Condicional' when  evT.name  = 'SCP_NON_FULFILLMENT' then  'Suspensión Condicional' else 'Suspensión Condicional' end, count(distinct ev.caseDetention.id)) " +
                "from Event ev " +
                "inner join ev.eventType evT " +
                "inner join ev.caseDetention c " +
                "inner join ev.caseDetention.monitoringPlan mP " +
                "where (ev.dateId between :initDate and :endDate) " +
                "and evT.name in ('SCP_PARTIAL_NON_FULFILLMENT','SCP_NON_FULFILLMENT') " +
                "and c.district.id = :districtId " +
                "and mP.supervisor.id = :supervisorId")
        List<SelectList> countSCPPWithNonFulfillBySupervisor(@Param("initDate") Integer initDate, @Param("endDate") Integer endDate, @Param("districtId") Long districtId, @Param("supervisorId") Long supervisorId);


        //SCPP general
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case when cahear.hearingFormatSpecs.arrangementType  = 2 then 'SCPP' else 'SCPP' end, count(distinct ca.id)) " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and cahear.isFinished = true " +
                "and cahear.hearingFormatSpecs.arrangementType = 2 " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "order by cahear.id desc")
        List<SelectList> countSCPP(@Param("initDate") Date initDate, @Param("endDate") Date endDate);


        //SCPP por distrito
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case when cahear.hearingFormatSpecs.arrangementType  = 2 then 'SCPP' else 'SCPP' end, count(distinct ca.id)) " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and cahear.isFinished = true " +
                "and cahear.hearingFormatSpecs.arrangementType = 2 " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "and ca.district.id = :districtId " +
                "order by cahear.id desc")
        List<SelectList> countWarningSCPPByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);

        //SCPP por distrito y operador
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case when cahear.hearingFormatSpecs.arrangementType  = 2 then 'SCPP' else 'SCPP' end, count(distinct ca.id)) " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and cahear.isFinished = true " +
                "and cahear.hearingFormatSpecs.arrangementType = 2 " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "and ca.district.id = :districtId " +
                "and ca.umecaSupervisor.id = :supervisorId " +
                "order by cahear.id desc")
        List<SelectList> countSCPPByDistrictAndSupervisor(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId, @Param("supervisorId") Long supervisorId);


        //SCPP sin supervisor
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case when cahear.hearingFormatSpecs.arrangementType  = 2 then 'SCPP' else 'SCPP' end, count(distinct ca.id)) " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and cahear.isFinished = true " +
                "and cahear.hearingFormatSpecs.arrangementType = 2 " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "and ca.district.id = :districtId " +
                "and ca.umecaSupervisor = null " +
                "order by cahear.id desc")
        List<SelectList> countSCPPByDistrictAndSupervisorNull(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);

        //Gender general
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case when caframe.personalData.gender  = 2 then 'Masculino' else 'Femenino' end, count(distinct ca.id)) " +
                "from Case ca " +
                "inner join ca.framingMeeting caframe " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "group by case when caframe.personalData.gender  = 2 then 'Masculino' else 'Femenino' end " +
                "order by caframe.personalData.gender desc")
        List<SelectList> countByGender(@Param("initDate") Date initDate, @Param("endDate") Date endDate);


        //Gender por distrito
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case when caframe.personalData.gender  = 2 then 'Masculino' else 'Femenino' end, count(distinct ca.id)) " +
                "from Case ca " +
                "inner join ca.framingMeeting caframe " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "and ca.district.id = :districtId " +
                "group by case when caframe.personalData.gender  = 2 then 'Masculino' else 'Femenino' end " +
                "order by caframe.personalData.gender desc")
        List<SelectList> countByGenderAndDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);


        //SCPP por distrito y operador
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case when caframe.personalData.gender  = 2 then 'Masculino' else 'Femenino' end, count(distinct ca.id)) " +
                "from Case ca " +
                "inner join ca.framingMeeting caframe " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "and ca.district.id = :districtId " +
                "and ca.umecaSupervisor.id = :supervisorId " +
                "group by case when caframe.personalData.gender  = 2 then 'Masculino' else 'Femenino' end " +
                "order by caframe.personalData.gender desc")
        List<SelectList> countByGenderAndDistrictAndSupervisor(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId, @Param("supervisorId") Long supervisorId);



        //Edad general
        @Query(value = "select " +
                "case " +
                "when age between 18 and 25 then '18 - 25' " +
                "when age between 26 and 30 then '26 - 30' " +
                "when age between 31 and 35 then '31 - 35' " +
                "when age between 36 and 40 then '36 - 40' " +
                "when age between 41 and 45 then '41 - 45' " +
                "when age between 46 and 50 then '46 - 50' " +
                "when age between 51 and 55 then '51 - 55' " +
                "when age between 56 and 60 then '56 - 60' " +
                "when age between 61 and 65 then '61 - 65' " +
                "when age between 66 and 70 then '66 - 70' " +
                "when age between 71 and 75 then '71 - 75' " +
                "when age between 76 and 80 then '76 - 80' " +
                "when age >= 80 then 'Más de 80' end as age_range, count(*) as count " +
                "from " +
                "(select TIMESTAMPDIFF(YEAR, frameImputed.birth_date, CURDATE()) AS age " +
                "from case_detention caseD " +
                "inner join framing_meeting frame on caseD.id_case = frame.id_case " +
                "inner join framing_imputed_personal_data frameImputed on frame.id_framing_imputed_personal_data = frameImputed.id_framing_imputed_personal_data " +
                "inner join cat_status_case statuscase on caseD.id_status = statuscase.id_status " +
                "where caseD.date_create between :initDate and :endDate " +
                "and caseD.id_status = statuscase.id_status " +
                "and (statuscase.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION', 'ST_CASE_CLOSE_REQUEST'))) as derived " +
                "group by age_range " +
                "order by age_range", nativeQuery = true)
        List<Object> countByAge(@Param("initDate") Date initDate, @Param("endDate") Date endDate);



        //Edad por distrito
        @Query(value = "select " +
                "case " +
                "when age between 18 and 25 then '18 - 25' " +
                "when age between 26 and 30 then '26 - 30' " +
                "when age between 31 and 35 then '31 - 35' " +
                "when age between 36 and 40 then '36 - 40' " +
                "when age between 41 and 45 then '41 - 45' " +
                "when age between 46 and 50 then '46 - 50' " +
                "when age between 51 and 55 then '51 - 55' " +
                "when age between 56 and 60 then '56 - 60' " +
                "when age between 61 and 65 then '61 - 65' " +
                "when age between 66 and 70 then '66 - 70' " +
                "when age between 71 and 75 then '71 - 75' " +
                "when age between 76 and 80 then '76 - 80' " +
                "when age >= 80 then 'Más de 80' end as age_range, count(*) as count " +
                "from " +
                "(select TIMESTAMPDIFF(YEAR, frameImputed.birth_date, CURDATE()) AS age " +
                "from case_detention caseD " +
                "inner join framing_meeting frame on caseD.id_case = frame.id_case " +
                "inner join framing_imputed_personal_data frameImputed on frame.id_framing_imputed_personal_data = frameImputed.id_framing_imputed_personal_data " +
                "inner join cat_status_case statuscase on caseD.id_status = statuscase.id_status " +
                "inner join cat_district dist on caseD.id_district = dist.id_district " +
                "where caseD.date_create between :initDate and :endDate " +
                "and caseD.id_status = statuscase.id_status " +
                "and dist.id_district = :districtId " +
                "and (statuscase.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION', 'ST_CASE_CLOSE_REQUEST'))) as derived " +
                "group by age_range " +
                "order by age_range", nativeQuery = true)
        List<Object> countByAgeAndDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);



        //Edad por distrito y supervisor
        @Query(value = "select " +
                "case " +
                "when age between 18 and 25 then 0 " +
                "when age between 26 and 30 then 1 " +
                "when age between 31 and 35 then 2 " +
                "when age between 36 and 40 then 3 " +
                "when age between 41 and 45 then 4 " +
                "when age between 46 and 50 then 5 " +
                "when age between 51 and 55 then 6 " +
                "when age between 56 and 60 then 7 " +
                "when age between 61 and 65 then 8 " +
                "when age between 66 and 70 then 9 " +
                "when age between 71 and 75 then 10 " +
                "when age between 76 and 80 then 11 " +
                "when age >= 80 then 12 else 12 end as id_age, " +
                "case " +
                "when age between 18 and 25 then '18 - 25' " +
                "when age between 26 and 30 then '26 - 30' " +
                "when age between 31 and 35 then '31 - 35' " +
                "when age between 36 and 40 then '36 - 40' " +
                "when age between 41 and 45 then '41 - 45' " +
                "when age between 46 and 50 then '46 - 50' " +
                "when age between 51 and 55 then '51 - 55' " +
                "when age between 56 and 60 then '56 - 60' " +
                "when age between 61 and 65 then '61 - 65' " +
                "when age between 66 and 70 then '66 - 70' " +
                "when age between 71 and 75 then '71 - 75' " +
                "when age between 76 and 80 then '76 - 80' " +
                "when age >= 80 then 'Más de 80' else 'xx' end as age_range, count(*) as count " +
                "from " +
                "(select TIMESTAMPDIFF(YEAR, frameImputed.birth_date, CURDATE()) AS age " +
                "from case_detention caseD " +
                "inner join framing_meeting frame on caseD.id_case = frame.id_case " +
                "inner join framing_imputed_personal_data frameImputed on frame.id_framing_imputed_personal_data = frameImputed.id_framing_imputed_personal_data " +
                "inner join cat_status_case statuscase on caseD.id_status = statuscase.id_status " +
                "inner join cat_district dist on caseD.id_district = dist.id_district " +
                "where caseD.date_create between :initDate and :endDate " +
                "and frame.id_user = :supervisorId " +
                "and caseD.id_status = statuscase.id_status " +
                "and dist.id_district = :districtId " +
                "and (statuscase.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION', 'ST_CASE_CLOSE_REQUEST'))) as derived " +
                "group by age_range " +
                "order by age_range", nativeQuery = true)
        List<Object> countByAgeAndDistrictAndSupervisor(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId, @Param("supervisorId") Long supervisorId);



        //crimes general
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case " +
                "when cahearcrime.crime.name = 'Robo' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo a casa habitación' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo calificado' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de autopartes' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de vehículo' then 'Robo' " +
                "else cahearcrime.crime.name end, count(cahearcrime.id)) " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "inner join cahear.crimeList cahearcrime " +
                "where cahear.id in (" +
                "select distinct cahear.id " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and cahear.isFinished = true " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "order by cahear.id desc" +
                ")" +
                "group by " +
                "case " +
                "when cahearcrime.crime.name = 'Robo' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo a casa habitación' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo calificado' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de autopartes' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de vehículo' then 'Robo' " +
                "else cahearcrime.crime.name end")
        List<SelectList> countCrimes(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

        //crimes por distrito
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case " +
                "when cahearcrime.crime.name = 'Robo' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo a casa habitación' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo calificado' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de autopartes' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de vehículo' then 'Robo' " +
                "else cahearcrime.crime.name end, count(cahearcrime.id)) " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "inner join cahear.crimeList cahearcrime " +
                "where cahear.id in (" +
                "select distinct cahear.id " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and ca.district.id = :districtId " +
                "and cahear.isFinished = true " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "order by cahear.id desc" +
                ")" +
                "group by " +
                "case " +
                "when cahearcrime.crime.name = 'Robo' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo a casa habitación' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo calificado' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de autopartes' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de vehículo' then 'Robo' " +
                "else cahearcrime.crime.name end")
        List<SelectList> countCrimesByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);

        //catalog por distrito
        @Query("select new com.umeca.model.shared.SelectList( cahearcrime.crime.id, " +
                "case " +
                "when cahearcrime.crime.name = 'Robo' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo a casa habitación' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo calificado' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de autopartes' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de vehículo' then 'Robo' " +
                "else cahearcrime.crime.name end) " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "inner join cahear.crimeList cahearcrime " +
                "where cahear.id in (" +
                "select distinct cahear.id " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and ca.district.id = :districtId " +
                "and cahear.isFinished = true " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "order by cahear.id desc" +
                ")" +
                "group by " +
                "case " +
                "when cahearcrime.crime.name = 'Robo' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo a casa habitación' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo calificado' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de autopartes' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de vehículo' then 'Robo' " +
                "else cahearcrime.crime.name end")
        List<SelectList> catalogCrimesByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);

        //crimes por supervisor y distrito
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case " +
                "when cahearcrime.crime.name = 'Robo' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo a casa habitación' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo calificado' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de autopartes' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de vehículo' then 'Robo' " +
                "else cahearcrime.crime.name end, count(cahearcrime.id)) " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "inner join cahear.crimeList cahearcrime " +
                "where cahear.id in (" +
                "select distinct cahear.id " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and ca.district.id = :districtId " +
                "and ca.umecaSupervisor.id = :supervisorId " +
                "and cahear.isFinished = true " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "order by cahear.id desc" +
                ")" +
                "group by " +
                "case " +
                "when cahearcrime.crime.name = 'Robo' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo a casa habitación' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo calificado' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de autopartes' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de vehículo' then 'Robo' " +
                "else cahearcrime.crime.name end")
        List<SelectList> countCrimesByDistrictAndSupervisor(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId, @Param("supervisorId") Long supervisorId);

        //crimes sin supervisor por distrito
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case " +
                "when cahearcrime.crime.name = 'Robo' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo a casa habitación' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo calificado' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de autopartes' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de vehículo' then 'Robo' " +
                "else cahearcrime.crime.name end, count(cahearcrime.id)) " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "inner join cahear.crimeList cahearcrime " +
                "where cahear.id in (" +
                "select distinct cahear.id " +
                "from Case ca " +
                "inner join ca.hearingFormats cahear " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and ca.district.id = :districtId " +
                "and ca.umecaSupervisor.id = null " +
                "and cahear.isFinished = true " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "order by cahear.id desc" +
                ")" +
                "group by " +
                "case " +
                "when cahearcrime.crime.name = 'Robo' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo a casa habitación' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo calificado' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de autopartes' then 'Robo' " +
                "when cahearcrime.crime.name = 'Robo de vehículo' then 'Robo' " +
                "else cahearcrime.crime.name end")
        List<SelectList> countCrimesByDistrictAndSupervisorNull(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);




        @Query("select new com.umeca.model.shared.SelectList(case when sch.block = false then 'No estudia' else 'Estudia' end, count(c.id)) " +
                "from Case  c " +
                "inner join c.framingMeeting fm " +
                "inner join c.status stCase " +
                "inner join fm.school sch " +
                "where fm.isTerminated = true " +
                "and fm.endDate between :initDate and :endDate " +
                "and stCase.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "group by sch.block")
        List<SelectList> countImputedStudyingGeneral(@Param("initDate") Date initDate, @Param("endDate") Date endDate);


        @Query("select new com.umeca.model.shared.SelectList(case when sch.block = false then 'No estudia' else 'Estudia' end, count(c.id)) " +
                "from Case  c " +
                "inner join c.framingMeeting fm " +
                "inner join c.status stCase " +
                "inner join fm.school sch " +
                "where fm.isTerminated = true " +
                "and fm.endDate between :initDate and :endDate " +
                "and stCase.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "and c.district.id = :districtId " +
                "group by sch.block")
        List<SelectList> countImputedStudyingByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);

        @Query("select new com.umeca.model.shared.SelectList(case when sch.block = false then 'No estudia' else 'Estudia' end, count(c.id)) " +
                "from Case  c " +
                "inner join c.framingMeeting fm " +
                "inner join c.status stCase " +
                "inner join fm.school sch " +
                "where fm.isTerminated = true " +
                "and fm.endDate between :initDate and :endDate " +
                "and stCase.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "and c.district.id = :districtId " +
                "and fm.supervisor.id = :supervisorId " +
                "group by sch.block")
        List<SelectList> countImputedStudyingBySupervisor(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId, @Param("supervisorId") Long supervisorId);


        @Query(value = "select " +
                "cat_academic_level.academic_level, " +
                "count(ResA.sub_id_case) " +
                "from cat_degree " +
                "inner join cat_academic_level on cat_degree.id_academic_level = cat_academic_level.id_academic_level " +
                "left join ( " +
                "select " +
                "       case_detention.id_case 'sub_id_case', " +
                "        school.id_grade as 'sub_id_grade' " +
                "from case_detention " +
                "inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "       and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true and (framing_meeting.end_date between :initDate and :endDate) " +
                "inner join school on school.id_framing_meeting = framing_meeting.id_framing_meeting " +
                ") ResA on cat_degree.id_degree = ResA.sub_id_grade " +
                "group by cat_academic_level.id_academic_level",nativeQuery = true)
        List<Object> countCasesBySchoolGradeGeneral(@Param("initDate") String initDate, @Param("endDate") String endDate);


        @Query(value = "select " +
                "cat_academic_level.academic_level, " +
                "count(ResA.sub_id_case) " +
                "from cat_degree " +
                "inner join cat_academic_level on cat_degree.id_academic_level = cat_academic_level.id_academic_level " +
                "left join ( " +
                "select " +
                "       case_detention.id_case 'sub_id_case', " +
                "        school.id_grade as 'sub_id_grade' " +
                "from case_detention " +
                "inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "       and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true and (framing_meeting.end_date between :initDate and :endDate) " +
                "inner join school on school.id_framing_meeting = framing_meeting.id_framing_meeting " +
                "where case_detention.id_district = :idDistrict " +
                ") ResA on cat_degree.id_degree = ResA.sub_id_grade " +
                "group by cat_academic_level.id_academic_level",nativeQuery = true)
        List<Object> countCasesBySchoolGradeAndDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict);


        @Query(value = "select " +
                "cat_academic_level.academic_level, " +
                "count(ResA.sub_id_case) " +
                "from cat_degree " +
                "inner join cat_academic_level on cat_degree.id_academic_level = cat_academic_level.id_academic_level " +
                "left join ( " +
                "select " +
                "       case_detention.id_case 'sub_id_case', " +
                "        school.id_grade as 'sub_id_grade' " +
                "from case_detention " +
                "inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "       and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true and framing_meeting.id_user = :idSupervisor  and (framing_meeting.end_date between :initDate and :endDate) " +
                "inner join school on school.id_framing_meeting = framing_meeting.id_framing_meeting " +
                "where case_detention.id_district = :idDistrict " +
                ") ResA on cat_degree.id_degree = ResA.sub_id_grade " +
                "group by cat_academic_level.id_academic_level",nativeQuery = true)
        List<Object> countCasesBySchoolGradeAndSupervisor(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict, @Param("idSupervisor") Long idSupervisor);


        //Changes in Arragenment Type General
        @Query(value = "select " +
                "case " +
                "when eventType.event='CHANGE_MC_TO_SCPP' then 'Cambio de MC a SCPP' " +
                "else 'Cambio de SCPP a MC' " +
                "end as evento," +
                "count(evento.id_case) as count " +
                "from event evento " +
                "inner join ( " +
                "select max(eventsub.id_event) as id_event " +
                "from event eventsub " +
                "inner join cat_event eventtype " +
                "where eventsub.event_id=eventtype.id_event " +
                "and (eventtype.event='CHANGE_MC_TO_SCPP' or eventtype.event='CHANGE_SCPP_TO_MC')" +
                "group by eventsub.id_case " +
                "order by eventsub.id_event desc " +
                ") table_events on evento.id_event = table_events.id_event " +
                "inner join case_detention caseD on evento.id_case = caseD.id_case " +
                "inner join cat_event eventType on evento.event_id = eventType.id_event " +
                "where caseD.date_create between :initDate and :endDate " +
                "group by case " +
                "when eventType.event='CHANGE_MC_TO_SCPP' then 'Cambio de MC a SCPP' " +
                "else 'Cambio de SCPP a MC' " +
                "end", nativeQuery = true)
        List<Object> countAnyChangesInArrangementType(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

        //Changes in Arragenment Type por distrito
        @Query(value = "select " +
                "case " +
                "when eventType.event='CHANGE_MC_TO_SCPP' then 'Cambio de MC a SCPP' " +
                "else 'Cambio de SCPP a MC' " +
                "end as evento," +
                "count(evento.id_case) as count " +
                "from event evento " +
                "inner join ( " +
                "select max(eventsub.id_event) as id_event " +
                "from event eventsub " +
                "inner join cat_event eventtype " +
                "where eventsub.event_id=eventtype.id_event " +
                "and (eventtype.event='CHANGE_MC_TO_SCPP' or eventtype.event='CHANGE_SCPP_TO_MC')" +
                "group by eventsub.id_case " +
                "order by eventsub.id_event desc " +
                ") table_events on evento.id_event = table_events.id_event " +
                "inner join case_detention caseD on evento.id_case = caseD.id_case " +
                "inner join cat_event eventType on evento.event_id = eventType.id_event " +
                "where caseD.date_create between :initDate and :endDate " +
                "and caseD.id_district = :districtId " +
                "group by case " +
                "when eventType.event='CHANGE_MC_TO_SCPP' then 'Cambio de MC a SCPP' " +
                "else 'Cambio de SCPP a MC' " +
                "end", nativeQuery = true)
        List<Object> countAnyChangesInArrangementTypeByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);

        //Changes in Arragenment Type por Supervisor y distrito
        @Query(value = "select " +
                "case " +
                "when eventType.event='CHANGE_MC_TO_SCPP' then 'Cambio de MC a SCPP' " +
                "else 'Cambio de SCPP a MC' " +
                "end as evento," +
                "count(evento.id_case) as count " +
                "from event evento " +
                "inner join ( " +
                "select max(eventsub.id_event) as id_event " +
                "from event eventsub " +
                "inner join cat_event eventtype " +
                "where eventsub.event_id=eventtype.id_event " +
                "and (eventtype.event='CHANGE_MC_TO_SCPP' or eventtype.event='CHANGE_SCPP_TO_MC')" +
                "group by eventsub.id_case " +
                "order by eventsub.id_event desc " +
                ") table_events on evento.id_event = table_events.id_event " +
                "inner join case_detention caseD on evento.id_case = caseD.id_case " +
                "inner join cat_event eventType on evento.event_id = eventType.id_event " +
                "where caseD.date_create between :initDate and :endDate " +
                "and caseD.id_district = :districtId " +
                "and caseD.id_umeca_supervisor = :idSupervisor " +
                "group by case " +
                "when eventType.event='CHANGE_MC_TO_SCPP' then 'Cambio de MC a SCPP' " +
                "else 'Cambio de SCPP a MC' " +
                "end", nativeQuery = true)
        List<Object> countAnyChangesInArrangementTypeByDistrictAndSupervisor(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId, @Param("idSupervisor") Long idSupervisor);

        //Changes in Arragenment Type por Supervisor y distrito
        @Query(value = "select " +
                "case " +
                "when eventType.event='CHANGE_MC_TO_SCPP' then 'Cambio de MC a SCPP' " +
                "else 'Cambio de SCPP a MC' " +
                "end as evento," +
                "count(evento.id_case) as count " +
                "from event evento " +
                "inner join ( " +
                "select max(eventsub.id_event) as id_event " +
                "from event eventsub " +
                "inner join cat_event eventtype " +
                "where eventsub.event_id=eventtype.id_event " +
                "and (eventtype.event='CHANGE_MC_TO_SCPP' or eventtype.event='CHANGE_SCPP_TO_MC')" +
                "group by eventsub.id_case " +
                "order by eventsub.id_event desc " +
                ") table_events on evento.id_event = table_events.id_event " +
                "inner join case_detention caseD on evento.id_case = caseD.id_case " +
                "inner join cat_event eventType on evento.event_id = eventType.id_event " +
                "where caseD.date_create between :initDate and :endDate " +
                "and caseD.id_district = :districtId " +
                "and caseD.id_umeca_supervisor = null " +
                "group by case " +
                "when eventType.event='CHANGE_MC_TO_SCPP' then 'Cambio de MC a SCPP' " +
                "else 'Cambio de SCPP a MC' " +
                "end", nativeQuery = true)
        List<Object> countAnyChangesInArrangementTypeByDistrictAndSupervisorNull(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);

        //sustraidos general
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case when ca.isSubstracted  = 1 then 'Sustraidos' else 'Sustraidos' end, count(distinct ca.id)) " +
                "from Case ca " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and ca.isSubstracted = 1 " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "")
        List<SelectList> countSubstracted(@Param("initDate") Date initDate, @Param("endDate") Date endDate);


        @Query("select new com.umeca.model.shared.SelectList(clC.name,count(clC.id)) " +
                "from Case c " +
                "inner join c.closeCause clC " +
                "where clC.code = com.umeca.model.shared.Constants.CAUSE_PRISON_MULTIPLE_FOLDER and c.closeDate between :initDate and :endDate ")
        List<SelectList> countSuspensionOfSupervisionForPreventivePrisonGeneral(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

        @Query(value = "select close_cause.name, count(case_detention.id_close_cause) from close_cause " +
                "left join case_detention " +
                "on close_cause.id_close_cause = case_detention.id_close_cause and case_detention.id_district = :idDistrict and close_cause.code = 'CAUSE_PRISION_MULTIPLE_FOLDER'  " +
                "and (case_detention.close_date between :initDate and :endDate)  ", nativeQuery = true)
        List<Object> countSuspensionOfSupervisionForPreventivePrisonByDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict);

        @Query(value = "select close_cause.name, count(case_detention.id_close_cause) from close_cause " +
                "left join case_detention " +
                "on close_cause.id_close_cause = case_detention.id_close_cause and case_detention.id_district = :idDistrict and close_cause.code = 'CAUSE_PRISION_MULTIPLE_FOLDER'  " +
                "and (case_detention.close_date between :initDate and :endDate) and case_detention.closer_user = :idSupervisor ", nativeQuery = true)
        List<Object> countSuspensionOfSupervisionForPreventivePrisonBySupervision(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict, @Param("idSupervisor") Long idSupervisor);

        //sustraidos por distrito
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case when ca.isSubstracted  = 1 then 'Sustraidos' else 'Sustraidos' end, count(distinct ca.id)) " +
                "from Case ca " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and ca.isSubstracted = 1 " +
                "and ca.district.id = :districtId " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "")
        List<SelectList> countSubstractedByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);

        //sustraidos por distrito y supervisor
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case when ca.isSubstracted  = 1 then 'Sustraidos' else 'Sustraidos' end, count(distinct ca.id)) " +
                "from Case ca " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and ca.isSubstracted = 1 " +
                "and ca.district.id = :districtId " +
                "and ca.umecaSupervisor.id = :idSupervisor " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "")
        List<SelectList> countSubstractedByDistrictAndSupervisor(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId, @Param("idSupervisor") Long idSupervisor);

        //sustraidos por distrito sin supervisor
        @Query("select new com.umeca.model.shared.SelectList(" +
                "case when ca.isSubstracted  = 1 then 'Sustraidos' else 'Sustraidos' end, count(distinct ca.id)) " +
                "from Case ca " +
                "where ca.dateCreate between :initDate and :endDate " +
                "and ca.isSubstracted = 1 " +
                "and ca.district.id = :districtId " +
                "and ca.umecaSupervisor.id = null " +
                "and ca.status.name in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "")
        List<SelectList> countSubstractedByDistrictAndSupervisorNull(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("districtId") Long districtId);

}
