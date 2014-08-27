package com.umeca.repository.shared;

import com.umeca.model.entities.shared.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/14/14
 * Time: 11:21 AM
 */
@Repository
public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
}
