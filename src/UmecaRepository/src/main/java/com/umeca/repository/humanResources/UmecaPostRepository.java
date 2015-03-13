package com.umeca.repository.humanResources;

import com.umeca.model.catalog.UmecaPost;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qUmecaPostRepository")
public interface UmecaPostRepository extends JpaRepository<UmecaPost, Long> {

    @Query("select new com.umeca.model.shared.SelectList(UP.id,UP.name,UP.specification) from UmecaPost UP " +
            "where UP.isObsolete=false order by UP.name asc")
    List<SelectList> findNoObsolete();
}