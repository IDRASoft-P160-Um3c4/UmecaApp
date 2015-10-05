package com.umeca.repository.channelingManager;

import com.umeca.model.catalog.StatisticChannelingReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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




}
