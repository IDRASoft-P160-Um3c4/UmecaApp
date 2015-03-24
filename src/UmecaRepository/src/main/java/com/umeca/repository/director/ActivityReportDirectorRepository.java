package com.umeca.repository.director;

import com.umeca.model.entities.director.activityReport.ActivityReportDirector;
import com.umeca.model.entities.shared.activityReport.ActivityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityReportDirectorRepository extends JpaRepository<ActivityReportDirector, Long> {
}
