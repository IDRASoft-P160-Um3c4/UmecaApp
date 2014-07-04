package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.ImputedHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("imputedHomeRepository")
public interface ImputedHomeRepository extends JpaRepository<ImputedHome,Long> {

}
