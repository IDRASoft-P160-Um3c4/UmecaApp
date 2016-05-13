package com.umeca.repository.catalog;

import com.umeca.model.catalog.CatChannelingInstitutionName;
import com.umeca.model.shared.SelectOptsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("channelingInstitutionNameRepository")
public interface ChannelingInstitutionNameRepository extends JpaRepository<CatChannelingInstitutionName, Long> {
    @Query("SELECT new com.umeca.model.shared.SelectOptsList(a.id, concat(a.name,' - ',a.description), ct.id) FROM CatChannelingInstitutionName a " +
            "INNER JOIN a.channelingType ct " +
            "WHERE a.isObsolete=false order by a.name, a.description")
    List<SelectOptsList> findNotObsolete();
}
