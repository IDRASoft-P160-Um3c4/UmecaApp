package com.umeca.repository.catalog;

import com.umeca.model.catalog.EventType;
import com.umeca.model.catalog.StatusCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository("eventRepository")
public interface EventRepository extends JpaRepository<EventType,Long> {
    @Query("SELECT s from EventType s where s.name=:code")
    public StatusCase findByCode(@Param("code") String code);
}
