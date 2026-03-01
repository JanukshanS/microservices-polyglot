package com.microservice.order_service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final List<Map<String, Object>> orders = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    @GetMapping
    public List<Map<String, Object>> getOrders() {
        return orders;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> placeOrder(@RequestBody Map<String, Object> order) {
        order.put("id", idCounter.getAndIncrement());
        order.put("status", "PENDING");
        orders.add(order);
        return ResponseEntity.status(201).body(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id) {
        return orders.stream()
                .filter(o -> {
                    Object value = o.get("id");
                    return (value instanceof Number) && ((Number) value).intValue() == id;
                })
                .findFirst()
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
