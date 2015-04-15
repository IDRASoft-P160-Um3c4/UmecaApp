package com.umeca.repository.director;

import com.umeca.model.entities.shared.Observation;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qObservationRepository")
public interface ObservationRepository extends JpaRepository<Observation, Long> {
    @Query("select new com.umeca.model.shared.SelectList(OB.registerDate, US.fullname, R.description, OB.comment) from Observation OB " +
            "inner join OB.registerUser US " +
            "inner join US.roles R " +
            "inner join OB.agreement AG " +
            "where AG.id=:agreementId order by OB.registerDate desc")
    public List<SelectList> getAllObsDtoByAgreementId(@Param("agreementId") Long agreementId);
}
