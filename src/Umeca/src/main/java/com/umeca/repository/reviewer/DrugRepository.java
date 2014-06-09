package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.Drug;
import com.umeca.model.entities.reviewer.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("drugRepository")
public interface DrugRepository extends JpaRepository<Drug,Long> {

}
