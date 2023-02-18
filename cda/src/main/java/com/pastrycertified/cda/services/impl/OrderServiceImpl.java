package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.dto.OrderDto;
import com.pastrycertified.cda.models.Orders;
import com.pastrycertified.cda.models.OrderStatusType;
import com.pastrycertified.cda.models.User;
import com.pastrycertified.cda.repository.OrderRepository;
import com.pastrycertified.cda.services.OrderService;
import com.pastrycertified.cda.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ObjectsValidator<OrderDto> validator;

    @Override
    public Integer save(OrderDto dto) {

        UUID callGenerateOrderNumber = generateOrderNumber();
        LocalDate test = LocalDate.now();
        validator.validate(dto);

        Orders order = OrderDto.toEntity(dto);
        order.setOrder_date(test);
        order.setOrder_delivered(order.getOrder_delivered());
        order.setOrder_number(callGenerateOrderNumber);
        order.setList_product(order.getList_product());
        order.setPrice(order.getPrice());
        order.setUser(
                User.builder()
                        .id(dto.getUser_id())
                        .build()
        );
        order.setOrder_status(String.valueOf(OrderStatusType.PENDING));

        return orderRepository.save(order).getId();
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(OrderDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(Integer id) {
        return orderRepository.findById(id)
                .map(OrderDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune commande ne correspond à cet id " + id));
    }

    @Override
    public Integer updateByid(Integer id, OrderDto dto) {
        return null;
    }

    @Override
    public List<OrderDto> findByUserId(Integer id) {
        return orderRepository.findByUserId(id)
                .stream()
                .map(OrderDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Integer updateOrderStatusByid(Integer id, OrderDto dto) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Aucune commande ne correspond à l'id " + id));
        if (dto.getOrder_status().equals(OrderStatusType.PENDING.toString())) {
            order.setOrder_status(OrderStatusType.PENDING.toString());
        }
        if (dto.getOrder_status().equals(OrderStatusType.INPREPARATION.toString())) {
            order.setOrder_status(OrderStatusType.INPREPARATION.toString());
        }
        if (dto.getOrder_status().equals(OrderStatusType.COMPLETED.toString())) {
            order.setOrder_status(OrderStatusType.COMPLETED.toString());
        }
        if (dto.getOrder_status().equals(OrderStatusType.DELIVERED.toString())) {
            order.setOrder_status(OrderStatusType.DELIVERED.toString());
        }
        if (dto.getOrder_status().equals(OrderStatusType.CANCELLED.toString())) {
            order.setOrder_status(OrderStatusType.CANCELLED.toString());
        }
        if (dto.getOrder_status() != null) {
            dto.setList_product(order.getList_product());
            dto.setOrder_date(order.getOrder_date());
            dto.setOrder_delivered(order.getOrder_delivered());
            dto.setPrice(order.getPrice());
            dto.setUser_id(order.getUser().getId());
        }
        orderRepository.save(order);
        return order.getId();
    }


    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

    public UUID generateOrderNumber() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }

}
