package com.umeca.repository.shared;

import com.umeca.model.entities.shared.activityReport.ActivityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityReportRepository extends JpaRepository<ActivityReport, Long> {
}
