package com.umeca.repository.shared;

import com.umeca.model.entities.shared.activityReport.ActivityReport;
import com.umeca.model.entities.shared.activityReport.ActivityReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qActivityReportRepository")
public interface ActivityReportRepository extends JpaRepository<ActivityReport, Long> {
    @Query("SELECT new com.umeca.model.entities.shared.activityReport.ActivityReportDto(ar.creationDate, ar.reportName, ar.description, u.fullname, upg.path, upg.realFileName, upg.fileName) " +
            "FROM ActivityReport ar " +
            "INNER JOIN ar.uploadFileGeneric upg " +
            "INNER JOIN ar.creatorUser u " +
            "WHERE ar.id IN :ids AND  ar.reportRole = :role")
    List<ActivityReportDto> getListOfFiles(@Param("ids")List<Long> lstIds, @Param("role")String role);
}
