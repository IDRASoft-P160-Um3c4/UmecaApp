package com.umeca.repository.catalog;

import com.umeca.model.catalog.CatChannelingInstitutionName;
import com.umeca.model.catalog.CatChannelingType;
import com.umeca.model.shared.SelectOptsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("channelingInstitutionNameRepository")
public interface ChannelingInstitutionNameRepository extends JpaRepository<CatChannelingInstitutionName, Long> {
    @Query("SELECT new com.umeca.model.shared.SelectOptsList(a.id, a.name, ct.id) FROM CatChannelingInstitutionName a " +
            "INNER JOIN a.channelingType ct " +
            "WHERE a.isObsolete=false")
    List<SelectOptsList> findNotObsolete();
}
