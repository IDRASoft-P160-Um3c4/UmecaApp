package com.umeca.repository.director;

import com.umeca.model.entities.director.activityReport.WizardActivityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardActivityReportRepository extends JpaRepository<WizardActivityReport, Long> {
}
