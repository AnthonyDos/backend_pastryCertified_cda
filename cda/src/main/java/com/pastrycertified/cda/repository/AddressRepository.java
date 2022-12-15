package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
