package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionsRepository extends JpaRepository<Options, Integer> {

    Optional<Options> findByTypeOption(String name);
}
