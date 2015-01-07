package com.umeca.repository.reviewer;
import com.umeca.model.entities.director.view.CaseRequestDto;
import com.umeca.model.entities.reviewer.CaseRequest;
import com.umeca.model.entities.reviewer.CoDefendant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CoDefendantRepository")
public interface CoDefendantRepository extends JpaRepository<CoDefendant,Long> {

}
