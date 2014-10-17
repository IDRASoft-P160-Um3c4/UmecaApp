package com.umeca.repository.catalog;

import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("relationshipRepository")
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {

    @Query("select r from Relationship as r where r.isObsolete=false order by r.name")
    List<Relationship> findNotObsolete();

    @Query("select r from Relationship as r where r.name=com.umeca.model.shared.Constants.NAME_RELATIONSHIP_IMPUTED")
    Relationship findImputedRelationship();

}
