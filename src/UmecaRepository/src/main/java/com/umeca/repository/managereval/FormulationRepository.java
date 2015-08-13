package com.umeca.repository.managereval;

import com.umeca.model.entities.managereval.Formulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("formulationRepository")
public interface FormulationRepository extends JpaRepository<Formulation,Long>{

}
