package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.SchoolLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("schoolLevelRepository")
public interface SchoolLevelRepository extends JpaRepository<SchoolLevel,Long> {

}
