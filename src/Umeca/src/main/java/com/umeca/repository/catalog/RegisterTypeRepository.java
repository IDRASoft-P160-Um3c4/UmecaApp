package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.RegisterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("registerTypeRepository")
public interface RegisterTypeRepository extends JpaRepository<RegisterType,Long> {

    @Query("select rt from RegisterType rt order by rt.name")
    List<RegisterType> findAllOrderByName();

}
