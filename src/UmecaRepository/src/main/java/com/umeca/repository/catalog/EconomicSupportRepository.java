package com.umeca.repository.catalog;

import com.umeca.model.catalog.CatEconomicSupport;
import com.umeca.model.catalog.CatEducationLevel;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("economicSupportRepository")
public interface EconomicSupportRepository extends JpaRepository<CatEconomicSupport, Long> {
    @Query("select new com.umeca.model.shared.SelectList(a.id, a.name) from CatEconomicSupport a where a.isObsolete=false")
    List<SelectList> findNotObsolete();
}
