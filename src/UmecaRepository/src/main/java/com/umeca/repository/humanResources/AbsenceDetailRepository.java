package com.umeca.repository.humanResources;

import com.umeca.model.entities.timeAttendance.AbsenceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 10/28/2015.
 */
public interface AbsenceDetailRepository extends JpaRepository<AbsenceDetail, Long> {

}
