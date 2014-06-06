package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.RegisterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("registerTypeRepository")
public interface RegisterTypeRepository extends JpaRepository<RegisterType,Long> {

}
