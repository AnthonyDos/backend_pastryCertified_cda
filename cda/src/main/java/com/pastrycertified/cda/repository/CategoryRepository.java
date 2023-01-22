package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.dto.CategoryDto;
import com.pastrycertified.cda.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findByName(String categoryName);
}
