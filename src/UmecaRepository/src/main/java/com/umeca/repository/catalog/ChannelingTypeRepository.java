package com.umeca.repository.catalog;

import com.umeca.model.catalog.CatChannelingType;
import com.umeca.model.catalog.CatPriority;
import com.umeca.model.shared.SelectList;
import com.umeca.model.shared.SelectOptsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("channelingTypeRepository")
public interface ChannelingTypeRepository extends JpaRepository<CatChannelingType, Long> {
    @Query("select new com.umeca.model.shared.SelectOptsList(a.id, a.name, a.institutionsTypesIds, a.code) from CatChannelingType a where a.isObsolete=false")
    List<SelectOptsList> findNotObsolete();

    @Query("select a.code from CatChannelingType a where a.id = :channelingTypeId  AND a.isObsolete=false")
    String getCodeById( @Param("channelingTypeId")Long channelingTypeId);
}
