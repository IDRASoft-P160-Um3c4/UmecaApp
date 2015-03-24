package com.umeca.repository.shared;

import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.shared.UploadFileGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadFileGenericRepository extends JpaRepository<UploadFileGeneric, Long> {
    @Query("SELECT COUNT(upg) FROM UploadFileGeneric upg WHERE upg.creationUser.id =:userId AND LOWER(upg.fileName) =:filename AND upg.isObsolete = false")
    Long alreadyExistFileByUser(@Param("userId") Long userId, @Param("filename") String filename);

    @Query("SELECT upg FROM UploadFileGeneric upg WHERE upg.creationUser.id =:userId AND upg.id =:id AND upg.isObsolete = false")
    UploadFileGeneric getValidUploadFileGenericByIdAndUserId(@Param("userId") Long userId, @Param("id") Long uploadFileGenericId);

    @Query("SELECT new com.umeca.model.entities.shared.UploadFileGeneric(upg.path, upg.realFileName, upg.fileName) FROM UploadFileGeneric upg WHERE upg.id =:id AND upg.isObsolete = false")
    UploadFileGeneric getPathAndFilename(@Param("id") Long id);

    @Query("SELECT new com.umeca.model.entities.shared.UploadFileGeneric(upg.path, upg.realFileName, upg.fileName) FROM UploadFileGeneric upg WHERE upg.creationUser.id =:userId AND upg.isObsolete = false")
    List<UploadFileGeneric> getUploadFilesByUserId(@Param("userId") Long userId);

    @Query("SELECT new com.umeca.model.entities.shared.UploadFileGeneric(upg.path, upg.realFileName, upg.fileName) from Employee E " +
            "inner join E.photo UPG " +
            "WHERE E.id =:idEmployee AND upg.isObsolete = false")
    UploadFileGeneric getPathAndFilenamePhotoByIdEmployee(@Param("idEmployee") Long id);
}
