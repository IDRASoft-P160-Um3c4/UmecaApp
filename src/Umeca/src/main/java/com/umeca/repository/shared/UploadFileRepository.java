package com.umeca.repository.shared;

import com.umeca.model.entities.shared.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/14/14
 * Time: 11:21 AM
 */
@Repository
public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
    @Query("SELECT COUNT(UP) FROM UploadFile UP INNER JOIN UP.caseDetention CD WHERE CD.id =:caseId AND UP.isObsolete = false")
    Long getNumberOfFilesByCase(@Param("caseId")Long caseId);

    @Query("SELECT COUNT(UP) FROM UploadFile UP INNER JOIN UP.caseDetention CD WHERE CD.id =:caseId AND LOWER(UP.fileName) =:filename AND UP.isObsolete = false")
    Long alreadyExistFileByCase(@Param("caseId")Long caseId, @Param("filename")String filename);

    @Query("SELECT SUM(UP.size) FROM UploadFile UP INNER JOIN UP.caseDetention CD WHERE CD.id =:caseId AND UP.isObsolete = false")
    Long getSizeFilesAlreadyIn(@Param("caseId")Long caseId);

    @Query("SELECT UP FROM UploadFile UP INNER JOIN UP.caseDetention CD WHERE CD.id =:caseId AND UP.id =:id AND UP.isObsolete = false")
    UploadFile getValidUploadFileById(@Param("caseId")Long caseId, @Param("id")Long uploadFileId);

    @Query("SELECT new com.umeca.model.entities.shared.UploadFile(UP.path, UP.realFileName, UP.fileName) FROM UploadFile UP WHERE UP.id =:id AND UP.isObsolete = false")
    UploadFile getPathAndFilename(@Param("id")Long id);

    @Query("SELECT new com.umeca.model.entities.shared.UploadFile(UP.path, UP.realFileName, UP.fileName) FROM UploadFile UP INNER JOIN UP.caseDetention CD WHERE CD.id =:caseId AND UP.isObsolete = false")
    List<UploadFile> getUploadFilesByCaseId(@Param("caseId")Long caseId);
}
