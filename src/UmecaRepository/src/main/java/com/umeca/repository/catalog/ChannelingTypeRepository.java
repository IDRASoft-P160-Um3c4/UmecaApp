package com.umeca.repository.catalog;

import com.umeca.model.catalog.CatChannelingType;
import com.umeca.model.catalog.CatPriority;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("channelingTypeRepository")
public interface ChannelingTypeRepository extends JpaRepository<CatChannelingType, Long> {
    @Query("select new com.umeca.model.shared.SelectList(a.id, a.name) from CatChannelingType a where a.isObsolete=false")
    List<SelectList> getChannelingType();
}
