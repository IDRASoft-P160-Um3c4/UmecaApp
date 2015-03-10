package com.umeca.repository.catalog;

import com.umeca.model.catalog.Activity;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.shared.SelectList;
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

    @Query("select r from Relationship as r where r.name=com.umeca.model.shared.Constants.NAME_RELATIONSHIP_OTHER")
    Relationship findOtherRelationship();

    @Query("select r from Relationship as r where r.name=com.umeca.model.shared.Constants.NAME_RELATIONSHIP_NONE")
    Relationship findNoneRelationship();

    @Query("select new com.umeca.model.shared.SelectList(R.id,R.name,R.specification) from Relationship R " +
            "where R.isObsolete=false order by R.name asc")
    List<SelectList> findNoObsolete();

}

