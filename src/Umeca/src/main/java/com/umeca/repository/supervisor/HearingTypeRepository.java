package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.AccompanimentInfo;
import com.umeca.model.entities.supervisor.HearingType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qHearingTypeRepository")
public interface HearingTypeRepository extends JpaRepository<HearingType, Long> {

    @Query("SELECT new com.umeca.model.shared.SelectList(HT.id,HT.description,HT.lock,HT.specification) FROM HearingType HT " +
            "WHERE HT.isObsolete=false order by HT.description")
    List<SelectList> getValidaHearingType();

}


