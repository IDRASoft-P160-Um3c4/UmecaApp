package com.umeca.repository.supervisorManager;

import com.umeca.model.catalog.StatisticSupervisorManagerReportType;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.shared.SelectList;
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
            "where hearing_format.register_timestamp between :initDate and :endDate and hearing_format.id_district = :districtId and hearing_format.id_user = :supervisorId and assigned_arrangement.id_arrangement = :arrangementId " +
            "group by hearing_format.id_case) and hearing_format.is_finished = true) " +
            "group by  cat_arrangement.id_arrangement", nativeQuery = true)
    List<Object> getArrangementByIdAndSupervisorId(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("supervisorId") Long supervisorId, @Param("arrangementId") Long arrangementId, @Param("districtId") Long districtId);


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
    @Query(value = "select drug, count(idDrug), idDrug " +
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
    @Query(value = "select drug, count(idDrug), idDrug " +
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


}
