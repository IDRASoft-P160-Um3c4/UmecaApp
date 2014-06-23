package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.CurrentCriminalProceeding;
import com.umeca.model.entities.reviewer.PreviousCriminalProceeding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("previousCriminalProceedingRepository")
public interface PreviousCriminalProceedingRepository extends JpaRepository<PreviousCriminalProceeding,Long> {

}
