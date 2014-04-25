package com.umeca.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umeca.model.catalog.Vialidad;



@Repository("vialidadRepository")
public interface VialidadRepository extends JpaRepository<Vialidad, Long>{
}


