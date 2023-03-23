package com.demo.controller.product;

import com.demo.dto.createedit.product.ProductFileCreateEditDto;
import com.demo.dto.read.product.ProductFileReadDto;
import com.demo.service.product.ProductFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product-files")
@RequiredArgsConstructor
public class ProductFileController {

    private final ProductFileService productFileService;

    @GetMapping
    public ResponseEntity<List<ProductFileReadDto>> findAllByProductId() {
        List<ProductFileReadDto> productFiles = this.productFileService.findAll();

        return new ResponseEntity<List<ProductFileReadDto>>(productFiles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ProductFileReadDto findById(@PathVariable("id") Long id) {
        return productFileService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductFileReadDto create(@RequestBody ProductFileCreateEditDto productFile) {
        return productFileService.create(productFile);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<ProductFileReadDto> update(@PathVariable("id") Long id, @RequestBody ProductFileCreateEditDto productFile) {

        return productFileService.update(id, productFile);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return productFileService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }

}
