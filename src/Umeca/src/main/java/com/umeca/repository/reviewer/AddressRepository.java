package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("addressRepository")
public interface AddressRepository extends JpaRepository<Address,Long> {

}
