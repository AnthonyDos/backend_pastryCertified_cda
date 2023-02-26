package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(value="SELECT * FROM `address` INNER JOIN user ON address.id_user = user.id WHERE address.id_user=:id", nativeQuery = true)
    Optional<Address> findAddressByIdUser(Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE address SET id_user= :userId WHERE address.id = :id", nativeQuery = true)
    void updateByIdAddress(Integer userId, Integer id);
}
