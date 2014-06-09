package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.DrugType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("drugTypeRepository")
public interface DrugTypeRepository extends JpaRepository<DrugType,Long> {

}
