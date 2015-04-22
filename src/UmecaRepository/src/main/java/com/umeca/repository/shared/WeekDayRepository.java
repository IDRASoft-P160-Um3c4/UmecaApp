package com.umeca.repository.shared;

import com.umeca.model.catalog.CatFileType;
import com.umeca.model.entities.shared.WeekDay;
import com.umeca.model.shared.SelectList;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qWeekDayRepository")
public interface WeekDayRepository extends JpaRepository<WeekDay, Long> {

    @Query("select new com.umeca.model.shared.SelectList(D.id, D.name) from WeekDay D where D.isObsolete = false")
    List<SelectList> findAllNoObsolete();

}
