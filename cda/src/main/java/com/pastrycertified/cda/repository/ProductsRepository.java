package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products,Integer> {

    Optional<Products>findByName(String name);

}