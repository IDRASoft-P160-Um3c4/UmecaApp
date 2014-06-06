package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.Drug;
import com.umeca.model.entities.reviewer.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("schoolRepository")
public interface SchoolRepository extends JpaRepository<School,Long> {

}
