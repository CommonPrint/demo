package com.demo.controller.payment;

import com.demo.dto.createedit.payment.PaymentDataCreateEditDto;
import com.demo.dto.read.payment.PaymentDataReadDto;
import com.demo.service.payment.PaymentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentDataController {

    private final PaymentDataService paymentDataService;

    @GetMapping
    public ResponseEntity<List<PaymentDataReadDto>> findAll() {
        List<PaymentDataReadDto> paymentDatas = this.paymentDataService.findAll();

        return new ResponseEntity<List<PaymentDataReadDto>>(paymentDatas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public PaymentDataReadDto findById(@PathVariable("id") Long id) {
        return paymentDataService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDataReadDto create(@RequestBody PaymentDataCreateEditDto paymentData) {
        return paymentDataService.create(paymentData);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<PaymentDataReadDto> update(@PathVariable("id") Long id, @RequestBody PaymentDataCreateEditDto paymentData) {

        return paymentDataService.update(id, paymentData);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return paymentDataService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }

}
