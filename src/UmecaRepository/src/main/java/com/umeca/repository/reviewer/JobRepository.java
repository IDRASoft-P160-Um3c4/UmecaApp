package com.umeca.repository.reviewer;

import com.umeca.model.entities.reviewer.Drug;
import com.umeca.model.entities.reviewer.Job;
import com.umeca.model.entities.reviewer.Schedule;
import com.umeca.model.entities.reviewer.dto.JobDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("jobRepository")
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("select sch from Job J inner join J.schedule sch where J.id=:idJob")
    List<Schedule> getJobSchedule(@Param("idJob") Long idJob);

    @Query("select new com.umeca.model.entities.reviewer.dto.JobDto(J.id, J.company,J.post,J.nameHead,J.salaryWeek, J.phone,J.start,J.end,E.id) from Job J " +
            "inner join J.employee E " +
            "where J.id =:idJob and E.id=:idEmployee")
    JobDto getDtoJobByIds(@Param("idEmployee") Long idEmployee, @Param("idJob") Long idJob);

}
