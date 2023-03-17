package com.demo.controller.product;

import lombok.RequiredArgsConstructor;

import com.demo.dto.createedit.product.ProductGuaranteeCreateEditDto;
import com.demo.dto.read.product.ProductGuaranteeReadDto;
import com.demo.service.product.ProductGuaranteeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/product-guarantee")
@RequiredArgsConstructor
public class ProductGuaranteeController {

    private final ProductGuaranteeService productGuaranteeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductGuaranteeReadDto> findAll() {
        return productGuaranteeService.findAll();
    }

    @GetMapping("/{id}")
    public ProductGuaranteeReadDto findById(@PathVariable("id") Long id) {
        return productGuaranteeService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductGuaranteeReadDto create(@RequestBody ProductGuaranteeCreateEditDto productGuarantee) {
        return productGuaranteeService.create(productGuarantee);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductGuaranteeReadDto update(@PathVariable("id") Long id,
                                          @RequestBody ProductGuaranteeCreateEditDto productGuarantee) {
        return productGuaranteeService.update(id, productGuarantee)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return productGuaranteeService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
