package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.FramingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qFramingAddressRepository")
public interface FramingAddressRepository extends JpaRepository<FramingAddress, Long> {

    @Query("select FA from FramingAddress FA inner join FA.address AD where AD.id=:addressId")
    FramingAddress findFramingAddressByIdAddress(@Param("addressId") Long addressId);

    @Query("select max(ADDR.id) from FramingMeeting FM " +
            "inner join FM.caseDetention CD " +
            "inner join FM.framingAddresses FA " +
            "inner join FA.address ADDR " +
            "where CD.id=:idCase")
    Long getLastIdAddressByIdCase(@Param("idCase") Long idCase);
}

