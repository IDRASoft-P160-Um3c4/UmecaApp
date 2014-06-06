package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("gradeRepository")
public interface GradeRepository extends JpaRepository<Grade,Long> {

}
