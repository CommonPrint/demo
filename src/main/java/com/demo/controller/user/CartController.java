package com.demo.controller.user;

import com.demo.dto.createedit.user.CartCreateEditDto;
import com.demo.dto.read.CityReadDto;
import com.demo.dto.read.user.CartReadDto;
import com.demo.service.user.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartReadDto>> findAll() {
        List<CartReadDto> carts = this.cartService.findAll();

        return new ResponseEntity<List<CartReadDto>>(carts, HttpStatus.OK);
    }


//    @GetMapping("/{id}")
//    public CartReadDto findById(@PathVariable("id") Long id) {
//        return cartService.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

//    @GetMapping("/cart-user/{userId}")
//    public CartReadDto findByUserId(@PathVariable("userId") Long userId) {
//
//        return cartService.findByUserId(userId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//    }

    // Правильный вариант поиска Корзины.
    // Ищем корзину через id-пользователя, т.к. на одного пользователя - одна корзина
    @GetMapping("/{userId}")
    public CartReadDto findByUserId(@PathVariable("userId") Long userId) {

        return cartService.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CartReadDto create(@RequestBody CartCreateEditDto cart) {
        return cartService.create(cart);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<CartReadDto> update(@PathVariable("id") Long id, @RequestBody CartCreateEditDto cart) {

        return cartService.update(id, cart);
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return cartService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }
    
}
