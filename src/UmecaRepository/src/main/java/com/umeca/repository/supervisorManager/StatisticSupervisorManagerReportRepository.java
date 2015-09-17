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

}
