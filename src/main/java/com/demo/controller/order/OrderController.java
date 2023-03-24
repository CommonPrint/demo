package com.demo.controller.order;

import com.demo.dto.createedit.order.OrderCreateEditDto;
import com.demo.dto.read.order.OrderReadDto;
import com.demo.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderReadDto>> findAll() {
        List<OrderReadDto> orders = this.orderService.findAll();

        return new ResponseEntity<List<OrderReadDto>>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public OrderReadDto findById(@PathVariable("id") Long id) {
        return orderService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderReadDto create(@RequestBody OrderCreateEditDto order) {
        return orderService.create(order);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<OrderReadDto> update(@PathVariable("id") Long id, @RequestBody OrderCreateEditDto order) {

        return orderService.update(id, order);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return orderService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }

}
