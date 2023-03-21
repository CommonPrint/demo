package com.demo.controller.product;


import com.demo.dto.createedit.CityCreateEditDto;
import com.demo.dto.createedit.product.ProductReviewCreateEditDto;
import com.demo.dto.read.CityReadDto;
import com.demo.dto.read.product.ProductReviewReadDto;
import com.demo.dto.read.product.StockReadDto;
import com.demo.service.product.ProductReviewService;
import com.demo.service.product.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ProductReviewController {

    private final ProductReviewService productReviewService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductReviewReadDto> findAll() {
        return productReviewService.findAll();
    }


    // Вернет отзывы по id продукта
    @GetMapping("/{id}")
    public ResponseEntity<List<ProductReviewReadDto>> findByProductId(@PathVariable("id") Long productId) {

        List<ProductReviewReadDto> reviews = this.productReviewService.findAllByProductId(productId);

        return new ResponseEntity<List<ProductReviewReadDto>>(reviews, HttpStatus.OK);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductReviewReadDto create(@RequestBody ProductReviewCreateEditDto review) {

        return productReviewService.create(review);
    }

    // Изменим отзыв пользователя по id
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<ProductReviewReadDto> update(@PathVariable("id") Long id, @RequestBody ProductReviewCreateEditDto review) {

        return productReviewService.update(id, review);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return productReviewService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }

}
