package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.DrugType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("drugTypeRepository")
public interface DrugTypeRepository extends JpaRepository<DrugType,Long> {

    @Query("select d from DrugType as d where isObsolete=false")
    List<DrugType> findNotObsolete();

}
