package com.demo.controller.product;

import com.demo.dto.createedit.product.ProductCreateEditDto;
import com.demo.dto.read.product.ProductReadDto;
import com.demo.entity.product.Product;
import com.demo.service.product.ProductService;
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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductReadDto>> findAll() {
        List<ProductReadDto> products = this.productService.findAll();

        return new ResponseEntity<List<ProductReadDto>>(products, HttpStatus.OK);
    }


    @GetMapping("/filter")
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        List<ProductReadDto> products = this.productService.findAll();
        Pageable paging = PageRequest.of(page, size);

        Page<ProductReadDto> pageProducts = this.productService.findAllPageable(paging);

        products = pageProducts.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("currentPage", pageProducts.getNumber());
        response.put("totalItems", pageProducts.getTotalElements());
        response.put("totalPages", pageProducts.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ProductReadDto findById(@PathVariable("id") Long id) {
        return productService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductReadDto create(@RequestBody ProductCreateEditDto product) {
        return productService.create(product);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<ProductReadDto> update(@PathVariable("id") Long id, @RequestBody ProductCreateEditDto product) {

        return productService.update(id, product);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return productService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }

}
