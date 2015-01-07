package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("maritalStatusRepository")
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus,Long> {

}
