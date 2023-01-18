package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
}
