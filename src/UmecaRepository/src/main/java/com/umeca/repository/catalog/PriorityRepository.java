package com.umeca.repository.catalog;

import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.CatPriority;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("priorityRepository")
public interface PriorityRepository extends JpaRepository<CatPriority, Long> {
    @Query("select new com.umeca.model.shared.SelectList(a.id, a.name, a.color) from CatPriority a where a.isObsolete=false")
    List<SelectList> getPriorities();
}
