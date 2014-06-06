package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.Domicile;
import com.umeca.model.entities.reviewer.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("domicileRepository")
public interface DomicileRepository extends JpaRepository<Domicile,Long> {

}
