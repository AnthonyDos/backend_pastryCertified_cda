package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.OrderDto;
import com.pastrycertified.cda.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${url.orders}")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("${create.order}")
    public ResponseEntity<Integer> save(
            @RequestBody OrderDto order
    ) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("${get.all.orders}")
    public ResponseEntity<List<OrderDto>>findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("${get.all.orders.by.userId}")
    public ResponseEntity<List<OrderDto>>findByUserId(
            @PathVariable("user_id") Integer userId
    ) {
        return ResponseEntity.ok(orderService.findByUserId(userId));
    }

    @GetMapping("${get.orders.by.id.order}")
    public ResponseEntity<OrderDto>findByOrderId(
            @PathVariable("order-id") Integer orderId
    ) {
        return ResponseEntity.ok(orderService.findById(orderId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("${update.order}")
    public ResponseEntity<Integer>updateById(
            @PathVariable("order-id") Integer orderId,
            @RequestBody OrderDto orderDto
    ) {
        return ResponseEntity.ok(orderService.updateByid(orderId, orderDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("${update.status.order}")
    public ResponseEntity<Integer>updateStatusOrder(
            @PathVariable("order-id") Integer orderId,
            @RequestBody OrderDto orderDto
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatusByid(orderId, orderDto));
    }

    @DeleteMapping("${delete.order.id}")
    public ResponseEntity<Void>delete(
            @PathVariable("order-id") Integer orderId
    ) {
        orderService.delete(orderId);
        return ResponseEntity.accepted().build();
    }
}
