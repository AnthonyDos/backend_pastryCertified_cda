package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    Optional<Shop> findByName(String name);
}
