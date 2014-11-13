package com.umeca.repository.catalog;
import com.umeca.model.catalog.TypeNameFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("typeNameFileRepository")
public interface TypeNameFileRepository extends JpaRepository<TypeNameFile,Long> {

}
