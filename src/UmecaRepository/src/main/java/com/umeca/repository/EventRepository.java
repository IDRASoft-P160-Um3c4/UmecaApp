package com.umeca.repository;

import com.umeca.model.entities.shared.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qCaseRepository")
public interface EventRepository extends JpaRepository<Event, Long> {

}