package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * cette interface intéragie entre la bdd et le service d'implémentation
 */
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findByName(String categoryName);
}
