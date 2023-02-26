package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * cette interface intéragie entre la bdd et le service d'implémentation
 */
public interface ShopRepository extends JpaRepository<Shop, Integer> {

    Optional<Shop> findByName(String name);
}
