package com.demo.mapper.read.user;

import com.demo.dto.read.product.ProductReadDto;
import com.demo.dto.read.user.FavoriteReadDto;
import com.demo.dto.read.user.UserReadDto;
import com.demo.entity.user.Favorite;
import com.demo.mapper.Mapper;
import com.demo.mapper.read.product.ProductReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FavoriteReadMapper implements Mapper<Favorite, FavoriteReadDto> {

    private final UserReadMapper userReadMapper;
    private final ProductReadMapper productReadMapper;

    @Override
    public FavoriteReadDto map(Favorite object) {

        UserReadDto user = Optional.ofNullable(object.getUser())
                .map(userReadMapper::map)
                .orElse(null);

        Set<ProductReadDto> products = object.getProducts().stream()
                .map(productReadMapper::map)
                .collect(Collectors.toSet());

        return new FavoriteReadDto(
                object.getId(),
                user,
                products
        );
    }
}
