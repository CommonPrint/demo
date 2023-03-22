package com.demo.controller.product;

import com.demo.dto.createedit.product.CategoryCreateEditDto;
import com.demo.dto.createedit.product.ProductTypeCreateEditDto;
import com.demo.dto.read.product.CategoryReadDto;
import com.demo.dto.read.product.ProductTypeReadDto;
import com.demo.service.product.CategoryService;
import com.demo.service.product.ProductTypeService;
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
@RequestMapping("/api/v1/product-type")
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductTypeReadDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductTypeReadDto findById(@PathVariable("id") Long id) {
        return productService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductTypeReadDto create(@RequestBody ProductTypeCreateEditDto product) {
        return productService.create(product);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductTypeReadDto update(@PathVariable("id") Long id,
                                 @RequestBody ProductTypeCreateEditDto product) {
        return productService.update(id, product)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return productService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
