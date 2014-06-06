package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.Drug;
import com.umeca.model.entities.reviewer.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jobRepository")
public interface JobRepository extends JpaRepository<Job,Long> {

}
