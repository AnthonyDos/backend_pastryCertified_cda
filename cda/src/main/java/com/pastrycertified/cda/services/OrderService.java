package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.OrderDto;

import java.util.List;

public interface OrderService extends AbstractService<OrderDto>{

    List<OrderDto> findByUserId(Integer userId);

    Integer updateOrderStatusByid(Integer id, OrderDto dto);
}
