package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.ResponseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("responseTypeRepository")
public interface ResponseTypeRepository extends JpaRepository<ResponseType,Long> {

    @Query("select e from ResponseType as e where e.name = :name")
    ResponseType findByCode(@Param("name")String response_type_pending);
}
