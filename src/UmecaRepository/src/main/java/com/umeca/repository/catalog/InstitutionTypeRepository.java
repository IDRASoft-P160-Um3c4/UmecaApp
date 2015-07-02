package com.umeca.repository.catalog;

import com.umeca.model.catalog.CatInstitutionType;
import com.umeca.model.shared.SelectOptsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("institutionTypeRepository")
public interface InstitutionTypeRepository extends JpaRepository<CatInstitutionType, Long> {
    @Query("select new com.umeca.model.shared.SelectOptsList(a.id, a.name, a.hasSpec) from CatInstitutionType a where a.isObsolete=false")
    List<SelectOptsList> findNotObsolete();
}
