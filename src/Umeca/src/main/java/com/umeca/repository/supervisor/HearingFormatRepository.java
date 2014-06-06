package com.umeca.repository.supervisor;

import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.HearingFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qHearingFormatRepository")
public interface HearingFormatRepository extends JpaRepository<HearingFormat, Long>{
}


