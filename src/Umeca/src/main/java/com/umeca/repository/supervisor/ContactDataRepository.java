package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.ContactData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qContactDataRepository")
public interface ContactDataRepository extends JpaRepository<ContactData, Long> {

    @Query("select CD from ContactData CD inner join CD.hearingFormat HF where HF.id =:idFormat")
    List<ContactData> getOldContacts(@Param("idFormat") Long idFormat);
}
