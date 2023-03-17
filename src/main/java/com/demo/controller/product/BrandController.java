package com.demo.controller.product;


import com.demo.dto.createedit.product.BrandCreateEditDto;
import com.demo.dto.read.product.BrandReadDto;
import com.demo.service.product.BrandService;
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
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BrandReadDto> findAll() {
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    public BrandReadDto findById(@PathVariable("id") Long id) {
        return brandService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BrandReadDto create(@RequestBody BrandCreateEditDto brand) {
        return brandService.create(brand);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BrandReadDto update(@PathVariable("id") Long id,
                                 @RequestBody BrandCreateEditDto brand) {
        return brandService.update(id, brand)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return brandService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
