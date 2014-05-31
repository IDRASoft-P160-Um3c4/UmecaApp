package com.umeca.repository.catalog;
import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("relationshipRepository")
public interface RelationshipRepository extends JpaRepository<Relationship,Long> {

}
