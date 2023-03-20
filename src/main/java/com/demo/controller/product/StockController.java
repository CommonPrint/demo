package com.demo.controller.product;


import com.demo.dto.createedit.product.StockCreateEditDto;
import com.demo.dto.read.product.StockReadDto;
import com.demo.service.product.StockService;
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
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StockReadDto> findAll() {
        return stockService.findAll();
    }

    @GetMapping("/{id}")
    public StockReadDto findByProductId(@PathVariable("id") Long id) {
        return stockService.findByProductId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public StockReadDto create(@RequestBody StockCreateEditDto stock) {
        return stockService.create(stock);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public StockReadDto update(@PathVariable("id") Long id,
                                 @RequestBody StockCreateEditDto stock) {
        return stockService.update(id, stock)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return stockService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
