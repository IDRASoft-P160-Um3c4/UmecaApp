package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.HearingFormatType;
import com.umeca.model.entities.supervisor.SupervisionActivity;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qHearingFormatTypeRepository")
public interface HearingFormatTypeRepository extends JpaRepository<HearingFormatType, Long>{

    @Query("SELECT hft FROM HearingFormatType hft WHERE hft.isObsolete=false")
    List<HearingFormatType> findAllValid();
}


