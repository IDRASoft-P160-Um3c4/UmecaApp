package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("requestTypeRepository")
public interface RequestTypeRepository extends JpaRepository<RequestType,Long> {

    @Query("select a from RequestType as a where a.isObsolete=false")
    List<RequestType> findNotObsolete();

    @Query("select r from RequestType as r where r.name=:code")
    RequestType findByCode(@Param("code")String code);
}
