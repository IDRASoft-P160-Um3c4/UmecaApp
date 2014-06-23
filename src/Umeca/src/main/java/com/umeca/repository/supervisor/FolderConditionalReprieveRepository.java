package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FolderConditionalReprieve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("qFolderConditionalRepository")
public interface FolderConditionalReprieveRepository extends JpaRepository<FolderConditionalReprieve, Long>{
}


