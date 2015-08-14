package com.umeca.repository.catalog;

import com.umeca.model.catalog.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository("eventTypeRepository")
public interface EventTypeRepository extends JpaRepository<EventType,Long> {

    @Query("SELECT e from EventType e where e.name=:code")
    public EventType findByCode(@Param("code") String code);

}
