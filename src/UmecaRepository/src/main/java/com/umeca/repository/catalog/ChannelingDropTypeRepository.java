package com.umeca.repository.catalog;

import com.umeca.model.catalog.CatChannelingDropType;
import com.umeca.model.catalog.CatChannelingType;
import com.umeca.model.shared.SelectList;
import com.umeca.model.shared.SelectOptsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("channelingDropTypeRepository")
public interface ChannelingDropTypeRepository extends JpaRepository<CatChannelingDropType, Long> {
    @Query("select new com.umeca.model.shared.SelectList(a.id, a.name) from CatChannelingDropType a where a.isObsolete=false")
    List<SelectList> findNotObsolete();
}
