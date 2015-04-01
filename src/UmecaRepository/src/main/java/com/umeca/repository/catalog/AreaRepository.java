package com.umeca.repository.catalog;

import com.umeca.model.catalog.Area;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qAreaRepository")
public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query("select new com.umeca.model.shared.SelectList(A.id,A.name,A.specification) from Area A " +
            "where A.isObsolete=false order by A.specification asc")
    List<SelectList> findNoObsolete();
}

