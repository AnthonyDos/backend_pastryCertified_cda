package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findByUserId(Integer id);

}
