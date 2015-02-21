package com.umeca.repository.supervisor;

import com.umeca.model.catalog.District;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qDistrictRepository")
public interface DistrictRepository extends JpaRepository<District, Long> {

    @Query("select new com.umeca.model.shared.SelectList(D.id,D.name) from District D where D.isObsolete=false")
    public List<SelectList> findNoObsolete();

}
