package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.CurrentCriminalProceeding;
import com.umeca.model.entities.reviewer.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("currentCriminalProceedingRepository")
public interface CurrentCriminalProceedingRepository extends JpaRepository<CurrentCriminalProceeding,Long> {

}
