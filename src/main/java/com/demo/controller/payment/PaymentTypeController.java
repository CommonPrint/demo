package com.demo.controller.payment;

import com.demo.dto.createedit.payment.PaymentTypeCreateEditDto;
import com.demo.dto.read.payment.PaymentTypeReadDto;
import com.demo.service.payment.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/payment-type")
@RequiredArgsConstructor
public class PaymentTypeController {

    private final PaymentTypeService paymentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaymentTypeReadDto> findAll() {
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public PaymentTypeReadDto findById(@PathVariable("id") Long id) {
        return paymentService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentTypeReadDto create(@RequestBody PaymentTypeCreateEditDto payment) {
        return paymentService.create(payment);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PaymentTypeReadDto update(@PathVariable("id") Long id,
                                 @RequestBody PaymentTypeCreateEditDto payment) {
        return paymentService.update(id, payment)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return paymentService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
