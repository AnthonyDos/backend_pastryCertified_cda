package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.OrderDto;

import java.util.List;

/**
 * cette interface intéragie entre le controller et le service d'implémentation
 */
public interface OrderService extends AbstractService<OrderDto>{

    List<OrderDto> findByUserId(Integer userId);

    Integer updateOrderStatusByid(Integer id, OrderDto dto);
}
