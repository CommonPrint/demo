package com.demo.mapper.read.user;

import com.demo.dto.read.user.UserReadDto;
import com.demo.dto.read.product.ProductReadDto;
import com.demo.dto.read.user.CartReadDto;
import com.demo.entity.user.Cart;
import com.demo.mapper.Mapper;
import com.demo.mapper.read.product.ProductReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartReadMapper implements Mapper<Cart, CartReadDto> {

    private final UserReadMapper userReadMapper;
    private final ProductReadMapper productReadMapper;

    @Override
    public CartReadDto map(Cart object) {

        UserReadDto user = Optional.ofNullable(object.getUser())
                .map(userReadMapper::map)
                .orElse(null);

        Set<ProductReadDto> products = object.getProducts().stream()
                .map(productReadMapper::map)
                .collect(Collectors.toSet());

        return new CartReadDto(
                object.getId(),
                user,
                products
        );
    }
}
