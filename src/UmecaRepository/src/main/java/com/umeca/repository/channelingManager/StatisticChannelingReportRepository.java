package com.umeca.repository.channelingManager;

import com.umeca.model.catalog.StatisticChannelingReportType;
import org.omg.CORBA.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.Object;
import java.util.Date;
import java.util.List;


@Repository("qStatisticChannelingReportRepository")
public interface StatisticChannelingReportRepository extends JpaRepository<StatisticChannelingReportType, Long> {


        //Channeling type general
        @Query(value = "select " +
                "cat_channeling_type.name, " +
                "count(ResA.sub_id_case) " +
                "from channeling " +
                "inner join( " +
                "select " +
                "distinct " +
                "    case_detention.id_case 'sub_id_case' " +
                "from case_detention " +
                "inner join  hearing_format " +
                "on hearing_format.id_case = case_detention.id_case and hearing_format.is_finished = true " +
                "inner join cat_status_case " +
                "on cat_status_case.id_status = case_detention.id_status " +
                "and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                ")ResA " +
                "on channeling.id_case = ResA.sub_id_case and channeling.creation_date between :initDate and :endDate " +
                "right join cat_channeling_type " +
                "on cat_channeling_type.id_cat_channeling_type = channeling.id_cat_channeling_type " +
                "group by cat_channeling_type.id_cat_channeling_type", nativeQuery = true)
        List<Object> countChannelingTypeGeneral(@Param("initDate") String initDate, @Param("endDate") String endDate);


        //channeling type por distrito
        @Query(value = "select " +
                "cat_channeling_type.name, " +
                "count(ResA.sub_id_case) " +
                "from channeling " +
                "inner join( " +
                "select " +
                "distinct " +
                "    case_detention.id_case 'sub_id_case' " +
                "from case_detention " +
                "inner join  hearing_format " +
                "on hearing_format.id_case = case_detention.id_case and hearing_format.is_finished = true " +
                "inner join cat_status_case " +
                "on cat_status_case.id_status = case_detention.id_status " +
                "and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "where case_detention.id_district = :idDistrict " +
                ")ResA " +
                "on channeling.id_case = ResA.sub_id_case and channeling.creation_date between :initDate and :endDate " +
                "right join cat_channeling_type " +
                "on cat_channeling_type.id_cat_channeling_type = channeling.id_cat_channeling_type " +
                "group by cat_channeling_type.id_cat_channeling_type", nativeQuery = true)
        List<Object> countChannelingTypeByDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict);


        //channeling type por supervisor y distrito
        @Query(value = "select " +
                "cat_channeling_type.name, " +
                "count(ResA.sub_id_case) " +
                "from channeling " +
                "inner join( " +
                "select " +
                "distinct " +
                "    case_detention.id_case 'sub_id_case' " +
                "from case_detention " +
                "inner join  hearing_format " +
                "on hearing_format.id_case = case_detention.id_case and hearing_format.is_finished = true " +
                "inner join cat_status_case " +
                "on cat_status_case.id_status = case_detention.id_status " +
                "and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "where case_detention.id_district = :idDistrict and case_detention.id_umeca_supervisor = :idSupervisor " +
                ")ResA " +
                "on channeling.id_case = ResA.sub_id_case and channeling.creation_date between :initDate and :endDate " +
                "right join cat_channeling_type " +
                "on cat_channeling_type.id_cat_channeling_type = channeling.id_cat_channeling_type " +
                "group by cat_channeling_type.id_cat_channeling_type", nativeQuery = true)
        List<Object> countChannelingTypeBySupervisor(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict, @Param("idSupervisor") Long idSupervisor);




        @Query(value = "select case activity_monitoring_plan.channeling_assistance when true then 'Asistencia' else 'Inasistencia' end as 'Asistencia/Inasistencia',  count(ResA.sub_id_case) from activity_monitoring_plan " +
                "inner join supervision_activity " +
                "on activity_monitoring_plan.id_supervision_activity = supervision_activity.id_supervision_activity and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                "inner join (select " +
                "                case_detention.id_case 'sub_id_case', " +
                "       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "       case_detention.id_district 'sub_id_district' " +
                "       from case_detention " +
                "       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                ") ResA on activity_monitoring_plan.id_case = ResA.sub_id_case " +
                "where activity_monitoring_plan.channeling_assistance is not null " +
                "group by activity_monitoring_plan.channeling_assistance", nativeQuery = true)
        List<Object> countAssistanceAndAbsenceGeneral(@Param("initDate") String initDate, @Param("endDate") String endDate);

        @Query(value = "select case activity_monitoring_plan.channeling_assistance when true then 'Asistencia' else 'Inasistencia' end as 'Asistencia/Inasistencia',  count(ResA.sub_id_case) from activity_monitoring_plan " +
                "inner join supervision_activity " +
                "on activity_monitoring_plan.id_supervision_activity = supervision_activity.id_supervision_activity and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                "inner join (select " +
                "                case_detention.id_case 'sub_id_case', " +
                "       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "       case_detention.id_district 'sub_id_district' " +
                "       from case_detention " +
                "       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                ") ResA on activity_monitoring_plan.id_case = ResA.sub_id_case and ResA.sub_id_district = :idDistrict " +
                "where activity_monitoring_plan.channeling_assistance is not null " +
                "group by activity_monitoring_plan.channeling_assistance" , nativeQuery = true)
        List<Object> countAssistanceAndAbsenceByDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict);


        @Query(value = "select case activity_monitoring_plan.channeling_assistance when true then 'Asistencia' else 'Inasistencia' end as 'Asistencia/Inasistencia',  count(ResA.sub_id_case) from activity_monitoring_plan " +
                "inner join supervision_activity " +
                "on activity_monitoring_plan.id_supervision_activity = supervision_activity.id_supervision_activity and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                "inner join (select " +
                "                case_detention.id_case 'sub_id_case', " +
                "       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "       case_detention.id_district 'sub_id_district' " +
                "       from case_detention " +
                "       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                ") ResA on activity_monitoring_plan.id_case = ResA.sub_id_case and ResA.sub_id_district = :idDistrict and ResA.sub_id_umeca_supervisor = :idSupervisor " +
                "where activity_monitoring_plan.channeling_assistance is not null " +
                "group by activity_monitoring_plan.channeling_assistance " , nativeQuery = true)
        List<Object>  countAssistanceAndAbsenceBySupervisor(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict, @Param("idSupervisor") Long idSupervisor);


        @Query(value = "select activity_goal.description, count(ResA.sub_id_case) from activity_monitoring_plan " +
                "inner join channeling " +
                "on activity_monitoring_plan.id_channeling = channeling.id_channeling " +
                "inner join activity_goal " +
                "on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_goal.name = 'Conclusión de la canalización'  and activity_monitoring_plan.status = 'REALIZADA' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                "inner join (select " +
                " case_detention.id_case 'sub_id_case', " +
                "                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                       case_detention.id_district 'sub_id_district' " +
                "                       from case_detention " +
                "                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case", nativeQuery = true)
        List<Object> countChannelingFinishedGeneral(@Param("initDate") String initDate, @Param("endDate") String endDate);


        @Query(value = "select activity_goal.description, count(ResA.sub_id_case) from activity_monitoring_plan " +
                "inner join channeling " +
                "on activity_monitoring_plan.id_channeling = channeling.id_channeling " +
                "inner join activity_goal " +
                "on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_goal.name = 'Conclusión de la canalización'  and activity_monitoring_plan.status = 'REALIZADA' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                "inner join (select " +
                " case_detention.id_case 'sub_id_case', " +
                "                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                       case_detention.id_district 'sub_id_district' " +
                "                       from case_detention " +
                "                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case  and ResA.sub_id_district = :idDistrict " , nativeQuery = true)
        List<Object> countChannelingFinishedByDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict);

        @Query(value = "select activity_goal.description, count(ResA.sub_id_case) from activity_monitoring_plan " +
                "inner join channeling " +
                "on activity_monitoring_plan.id_channeling = channeling.id_channeling " +
                "inner join activity_goal " +
                "on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_goal.name = 'Conclusión de la canalización'  and activity_monitoring_plan.status = 'REALIZADA' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                " inner join (select " +
                "case_detention.id_case 'sub_id_case', " +
                "                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                       case_detention.id_district 'sub_id_district' " +
                "                       from case_detention " +
                "                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case and ResA.sub_id_district = :idDistrict and ResA.sub_id_umeca_supervisor = :idSupervisor ", nativeQuery = true)
        List<Object> countChannelingFinishedBySupervisor(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict, @Param("idSupervisor") Long idSupervisor);



        @Query(value = "select case activity_monitoring_plan.channeling_assistance when true then 'Asistencia' else 'Inasistencia' end as 'Asistencia/Inasistencia',  count(ResA.sub_id_case) from activity_monitoring_plan " +
                "inner join channeling " +
                "on activity_monitoring_plan.id_channeling = channeling.id_channeling " +
                "inner join activity_goal " +
                "on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_monitoring_plan.status = 'REALIZADA' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                " inner join (select " +
                "case_detention.id_case 'sub_id_case', " +
                "                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                       case_detention.id_district 'sub_id_district' " +
                "                       from case_detention " +
                "                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case " +
                "                where activity_monitoring_plan.channeling_assistance is not null " +
                "                group by activity_monitoring_plan.channeling_assistance", nativeQuery = true)
        List<Object> countAbsenceChannelingGeneral(@Param("initDate") String initDate, @Param("endDate") String endDate);






        @Query(value = "select case activity_monitoring_plan.channeling_assistance when true then 'Asistencia' else 'Inasistencia' end as 'Asistencia/Inasistencia',  count(ResA.sub_id_case) from activity_monitoring_plan " +
                "inner join channeling " +
                "on activity_monitoring_plan.id_channeling = channeling.id_channeling " +
                "inner join activity_goal " +
                "on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_monitoring_plan.status = 'REALIZADA' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                " inner join (select " +
                "case_detention.id_case 'sub_id_case', " +
                "                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                       case_detention.id_district 'sub_id_district' " +
                "                       from case_detention " +
                "                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case and ResA.sub_id_district = :idDistrict and ResA.sub_id_umeca_supervisor = :idSupervisor  " +
                "                where activity_monitoring_plan.channeling_assistance is not null " +
                "                group by activity_monitoring_plan.channeling_assistance", nativeQuery = true)
        List<Object> countAbsenceChannelingBySupervisor(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict, @Param("idSupervisor") Long idSupervisor);



        @Query(value = "select cat_channeling_type.name, count(activity_monitoring_plan.id_case) from activity_monitoring_plan " +
                "inner join channeling " +
                "on activity_monitoring_plan.id_channeling = channeling.id_channeling " +
                "inner join activity_goal " +
                "on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_monitoring_plan.status = 'REALIZADA' and activity_goal.name = 'Baja de la canalización' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                " inner join (select " +
                " case_detention.id_case 'sub_id_case', " +
                "                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                       case_detention.id_district 'sub_id_district' " +
                "                       from case_detention " +
                "                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case " +
                "right join cat_channeling_type " +
                "on cat_channeling_type.id_cat_channeling_type = channeling.id_cat_channeling_type " +
                "group by cat_channeling_type.id_cat_channeling_type ", nativeQuery = true)
        List<Object> countChannelingDesertedGeneral(@Param("initDate") String initDate, @Param("endDate") String endDate);

        @Query(value = "select cat_channeling_type.name, count(activity_monitoring_plan.id_case) from activity_monitoring_plan " +
                "inner join channeling " +
                "on activity_monitoring_plan.id_channeling = channeling.id_channeling " +
                "inner join activity_goal " +
                "on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_monitoring_plan.status = 'REALIZADA' and activity_goal.name = 'Baja de la canalización' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                " inner join (select " +
                " case_detention.id_case 'sub_id_case', " +
                "                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                       case_detention.id_district 'sub_id_district' " +
                "                       from case_detention " +
                "                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case and ResA.sub_id_district = :idDistrict " +
                "right join cat_channeling_type " +
                "on cat_channeling_type.id_cat_channeling_type = channeling.id_cat_channeling_type " +
                "group by cat_channeling_type.id_cat_channeling_type", nativeQuery = true)
        List<Object> countChannelingDesertedByDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict);


        @Query(value = "select cat_channeling_type.name, count(activity_monitoring_plan.id_case) from activity_monitoring_plan " +
                "inner join channeling " +
                "on activity_monitoring_plan.id_channeling = channeling.id_channeling " +
                "inner join activity_goal " +
                "on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_monitoring_plan.status = 'REALIZADA' and activity_goal.name = 'Baja de la canalización' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                "inner join (select " +
                "case_detention.id_case 'sub_id_case', " +
                "                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor'," +
                "                       case_detention.id_district 'sub_id_district' " +
                "                       from case_detention " +
                "                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case and ResA.sub_id_district = :idDistrict and ResA.sub_id_umeca_supervisor = :idSupervisor " +
                "right join cat_channeling_type " +
                "on cat_channeling_type.id_cat_channeling_type = channeling.id_cat_channeling_type " +
                "group by cat_channeling_type.id_cat_channeling_type", nativeQuery = true)
        List<Object> countChannelingDesertedBySupervisor(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idDistrict") Long idDistrict, @Param("idSupervisor") Long idSupervisor);


        //Consultas ultima versión

        //Canalizados
        @Query(value = "select " +
                " 'Canalizados', " +
                "SUM(if(hasChanneling = 1, 1, 0)) AS 'Channeling' " +
                "FROM( " +
                "select " +
                "distinct " +
                "case_detention.id_case, " +
                "if(channeling.id_channeling IS NULL, 0, 1) AS 'hasChanneling', " +
                "case_detention.id_district, " +
                "case_detention.id_umeca_supervisor " +
                "from case_detention " +
                "inner join hearing_format " +
                "                        on hearing_format.id_case = case_detention.id_case and hearing_format.is_finished = true " +
                "                        inner join cat_status_case " +
                "                        on cat_status_case.id_status = case_detention.id_status and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                        left join channeling on case_detention.id_case = channeling.id_case and channeling.creation_date between :initDate and :endDate  and channeling.id_cat_channeling_type = :idChannelingType " +
                "                        and case_detention.id_district = :idDistrict " +
                "                        ) ResA", nativeQuery = true)
        List<Object> countChannelingCasesByTypeByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate,@Param("idDistrict") Long idDistrict ,@Param("idChannelingType") Long idChannelingType);

        //Primera cita
        @Query(value = "select case activity_monitoring_plan.channeling_assistance when true then 'Asistencia' else 'Inasistencia' end as 'Asistencia/Inasistencia',  count(ResA.sub_id_case) from activity_monitoring_plan " +
                "                inner join supervision_activity " +
                "                on activity_monitoring_plan.id_supervision_activity = supervision_activity.id_supervision_activity and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                "                inner join activity_goal on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_goal.name = 'Primer cita canalización' and activity_monitoring_plan.status = 'REALIZADA'\n" +
                "               inner join channeling on  activity_monitoring_plan.id_channeling = channeling.id_channeling and channeling.id_cat_channeling_type = :idChannelingType " +
                "                inner join (select " +
                "                                case_detention.id_case 'sub_id_case', " +
                "                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                       case_detention.id_district 'sub_id_district' " +
                "                       from case_detention " +
                "                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true \n" +
                "                 )ResA on activity_monitoring_plan.id_case = ResA.sub_id_case and ResA.sub_id_district = :idDistrict " +
                "                where activity_monitoring_plan.channeling_assistance is not null " +
                "                group by activity_monitoring_plan.channeling_assistance " +
                "                order by 'Asistencia/Inasistencia'", nativeQuery = true)
        List<Object> countAssistanceFirstDateByDistrict(@Param("initDate") Date initDate, @Param("endDate") Date endDate,@Param("idDistrict") Long idDistrict, @Param("idChannelingType") Long idChannelingType);

        //Asistencias Inasistencias
        @Query(value = "select case activity_monitoring_plan.channeling_assistance when true then 'Asistencia' else 'Inasistencia' end as 'Asistencia/Inasistencia',  count(ResA.sub_id_case) from activity_monitoring_plan " +
                "inner join channeling " +
                "on activity_monitoring_plan.id_channeling = channeling.id_channeling and channeling.id_cat_channeling_type = :idChannelingType " +
                "inner join activity_goal " +
                "on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_monitoring_plan.status = 'REALIZADA' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                " inner join (select " +
                "case_detention.id_case 'sub_id_case', " +
                "                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                       case_detention.id_district 'sub_id_district' " +
                "                       from case_detention " +
                "                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case and ResA.sub_id_district = :idDistrict " +
                "                where activity_monitoring_plan.channeling_assistance is not null " +
                "                group by activity_monitoring_plan.channeling_assistance " +
                "                order by 'Asistencia/Inasistencia'", nativeQuery = true)
        List<Object> countAbsenceChannelingByDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate,@Param("idDistrict") Long idDistrict, @Param("idChannelingType") Long idChannelingType);

        //Bajas
        @Query(value = "select cat_channeling_type.name, count(activity_monitoring_plan.id_case) from activity_monitoring_plan " +
                "                inner join channeling " +
                "                on activity_monitoring_plan.id_channeling = channeling.id_channeling " +
                "                inner join activity_goal " +
                "                on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_monitoring_plan.status = 'REALIZADA' and activity_goal.name = 'Baja de la canalización' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                "                inner join (select " +
                "       case_detention.id_case 'sub_id_case', " +
                "                                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                                       case_detention.id_district 'sub_id_district' " +
                "                                       from case_detention " +
                "                                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case and ResA.sub_id_district = :idDistrict " +
                "                right join cat_channeling_type " +
                "                on cat_channeling_type.id_cat_channeling_type = channeling.id_cat_channeling_type " +
                "                where cat_channeling_type.id_cat_channeling_type = :idChannelingType " +
                "                group by cat_channeling_type.id_cat_channeling_type", nativeQuery = true)
        List<Object> countChannelingDesertByTypeByDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate,@Param("idDistrict") Long idDistrict, @Param("idChannelingType") Long idChannelingType);


        //Altas
        @Query(value = "select cat_channeling_type.name, count(activity_monitoring_plan.id_case) from activity_monitoring_plan " +
                "                inner join channeling " +
                "                on activity_monitoring_plan.id_channeling = channeling.id_channeling " +
                "                inner join activity_goal " +
                "                on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_monitoring_plan.status = 'REALIZADA' and activity_goal.name = 'Conclusión de la canalización' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                "                inner join (select " +
                "       case_detention.id_case 'sub_id_case', " +
                "                                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                                       case_detention.id_district 'sub_id_district' " +
                "                                       from case_detention " +
                "                                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case and ResA.sub_id_district = :idDistrict" +
                "                right join cat_channeling_type " +
                "                on cat_channeling_type.id_cat_channeling_type = channeling.id_cat_channeling_type " +
                "                where cat_channeling_type.id_cat_channeling_type = :idChannelingType " +
                "                group by cat_channeling_type.id_cat_channeling_type", nativeQuery = true)
        List<Object> countChannelingFinishedByTypeByDistrict(@Param("initDate") String initDate, @Param("endDate") String endDate,@Param("idDistrict") Long idDistrict, @Param("idChannelingType") Long idChannelingType);


        //GENERAL
        //Consultas ultima versión

        //Canalizados
        @Query(value = "select " +
                " 'Canalizados', " +
                "SUM(if(hasChanneling = 1, 1, 0)) AS 'Channeling' " +
                "FROM( " +
                "select " +
                "distinct " +
                "case_detention.id_case, " +
                "if(channeling.id_channeling IS NULL, 0, 1) AS 'hasChanneling', " +
                "case_detention.id_district, " +
                "case_detention.id_umeca_supervisor " +
                "from case_detention " +
                "inner join hearing_format " +
                "                        on hearing_format.id_case = case_detention.id_case and hearing_format.is_finished = true " +
                "                        inner join cat_status_case " +
                "                        on cat_status_case.id_status = case_detention.id_status and cat_status_case.status in ('ST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                        left join channeling on case_detention.id_case = channeling.id_case and channeling.creation_date between :initDate and :endDate  and channeling.id_cat_channeling_type = :idChannelingType " +
                "                        ) ResA", nativeQuery = true)
        List<Object> countChannelingCasesByTypeGeneral(@Param("initDate") Date initDate, @Param("endDate") Date endDate,@Param("idChannelingType") Long idChannelingType);

        //Primera cita
        @Query(value = "select case activity_monitoring_plan.channeling_assistance when true then 'Asistencia' else 'Inasistencia' end as 'Asistencia/Inasistencia',  count(ResA.sub_id_case) from activity_monitoring_plan " +
                "                inner join supervision_activity " +
                "                on activity_monitoring_plan.id_supervision_activity = supervision_activity.id_supervision_activity and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                "                inner join activity_goal on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_goal.name = 'Primer cita canalización' and activity_monitoring_plan.status = 'REALIZADA'\n" +
                "               inner join channeling on  activity_monitoring_plan.id_channeling = channeling.id_channeling and channeling.id_cat_channeling_type = :idChannelingType " +
                "                inner join (select " +
                "                                case_detention.id_case 'sub_id_case', " +
                "                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                       case_detention.id_district 'sub_id_district' " +
                "                       from case_detention " +
                "                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true \n" +
                "                 )ResA on activity_monitoring_plan.id_case = ResA.sub_id_case " +
                "                where activity_monitoring_plan.channeling_assistance is not null " +
                "                group by activity_monitoring_plan.channeling_assistance " +
                "                order by 'Asistencia/Inasistencia'", nativeQuery = true)
        List<Object> countAssistanceFirstDateGeneral(@Param("initDate") Date initDate, @Param("endDate") Date endDate, @Param("idChannelingType") Long idChannelingType);

        //Asistencias Inasistencias
        @Query(value = "select case activity_monitoring_plan.channeling_assistance when true then 'Asistencia' else 'Inasistencia' end as 'Asistencia/Inasistencia',  count(ResA.sub_id_case) from activity_monitoring_plan " +
                "inner join channeling " +
                "on activity_monitoring_plan.id_channeling = channeling.id_channeling and channeling.id_cat_channeling_type = :idChannelingType " +
                "inner join activity_goal " +
                "on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_monitoring_plan.status = 'REALIZADA' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                " inner join (select " +
                "case_detention.id_case 'sub_id_case', " +
                "                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                       case_detention.id_district 'sub_id_district' " +
                "                       from case_detention " +
                "                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case " +
                "                where activity_monitoring_plan.channeling_assistance is not null " +
                "                group by activity_monitoring_plan.channeling_assistance " +
                "                order by 'Asistencia/Inasistencia'", nativeQuery = true)
        List<Object> countAbsenceChannelingGeneral(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idChannelingType") Long idChannelingType);

        //Bajas
        @Query(value = "select cat_channeling_type.name, count(activity_monitoring_plan.id_case) from activity_monitoring_plan " +
                "                inner join channeling " +
                "                on activity_monitoring_plan.id_channeling = channeling.id_channeling " +
                "                inner join activity_goal " +
                "                on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_monitoring_plan.status = 'REALIZADA' and activity_goal.name = 'Baja de la canalización' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                "                inner join (select " +
                "       case_detention.id_case 'sub_id_case', " +
                "                                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                                       case_detention.id_district 'sub_id_district' " +
                "                                       from case_detention " +
                "                                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case " +
                "                right join cat_channeling_type " +
                "                on cat_channeling_type.id_cat_channeling_type = channeling.id_cat_channeling_type " +
                "                where cat_channeling_type.id_cat_channeling_type = :idChannelingType " +
                "                group by cat_channeling_type.id_cat_channeling_type", nativeQuery = true)
        List<Object> countChannelingDesertByTypeGeneral(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idChannelingType") Long idChannelingType);


        //Altas
        @Query(value = "select cat_channeling_type.name, count(activity_monitoring_plan.id_case) from activity_monitoring_plan " +
                "                inner join channeling " +
                "                on activity_monitoring_plan.id_channeling = channeling.id_channeling " +
                "                inner join activity_goal " +
                "                on activity_monitoring_plan.id_activity_goal = activity_goal.id_activity_goal and activity_monitoring_plan.status = 'REALIZADA' and activity_goal.name = 'Conclusión de la canalización' and (activity_monitoring_plan.done_time between :initDate and :endDate) " +
                "                inner join (select " +
                "       case_detention.id_case 'sub_id_case', " +
                "                                       case_detention.id_umeca_supervisor 'sub_id_umeca_supervisor', " +
                "                                       case_detention.id_district 'sub_id_district' " +
                "                                       from case_detention " +
                "                                       inner join cat_status_case on cat_status_case.id_status = case_detention.id_status " +
                "                                       and cat_status_case.status in ('actorST_CASE_HEARING_FORMAT_END' , 'ST_CASE_FRAMING_MEETING_INCOMPLETE', 'ST_CASE_FRAMING_MEETING_COMPLETE', 'ST_CASE_REQUEST', 'ST_CASE_REQUEST_SUPERVISION','ST_CASE_CLOSE_REQUEST') " +
                "                                       inner join framing_meeting on framing_meeting.id_case = case_detention.id_case and framing_meeting.is_terminated = true " +
                "                                ) ResA on activity_monitoring_plan.id_case = ResA.sub_id_case " +
                "                right join cat_channeling_type " +
                "                on cat_channeling_type.id_cat_channeling_type = channeling.id_cat_channeling_type " +
                "                where cat_channeling_type.id_cat_channeling_type = :idChannelingType " +
                "                group by cat_channeling_type.id_cat_channeling_type", nativeQuery = true)
        List<Object> countChannelingFinishedByTypeGeneral(@Param("initDate") String initDate, @Param("endDate") String endDate, @Param("idChannelingType") Long idChannelingType);


}
