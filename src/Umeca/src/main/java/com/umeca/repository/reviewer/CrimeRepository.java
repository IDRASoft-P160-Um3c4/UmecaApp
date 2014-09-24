package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.Crime;
import com.umeca.model.entities.reviewer.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("crimeRepository")
public interface CrimeRepository extends JpaRepository<Crime,Long> {

}
