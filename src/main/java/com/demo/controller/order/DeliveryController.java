package com.demo.controller.order;

import com.demo.dto.createedit.order.DeliveryCreateEditDto;
import com.demo.dto.read.order.DeliveryReadDto;
import com.demo.service.order.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<List<DeliveryReadDto>> findAll() {
        List<DeliveryReadDto> deliverys = this.deliveryService.findAll();

        return new ResponseEntity<List<DeliveryReadDto>>(deliverys, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public DeliveryReadDto findById(@PathVariable("id") Long id) {
        return deliveryService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryReadDto create(@RequestBody DeliveryCreateEditDto delivery) {
        return deliveryService.create(delivery);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<DeliveryReadDto> update(@PathVariable("id") Long id, @RequestBody DeliveryCreateEditDto delivery) {

        return deliveryService.update(id, delivery);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return deliveryService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }

}
