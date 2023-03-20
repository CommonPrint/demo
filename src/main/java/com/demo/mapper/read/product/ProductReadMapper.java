package com.demo.mapper.read.product;


import com.demo.dto.read.product.CategoryReadDto;
import com.demo.dto.read.product.ProductReadDto;
import com.demo.dto.read.product.ProductTypeReadDto;
import com.demo.dto.read.product.ShareReadDto;
import com.demo.entity.product.Product;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductReadMapper implements Mapper<Product, ProductReadDto> {

    private final CategoryReadMapper categoryReadMapper;
    private final ShareReadMapper shareReadMapper;
    private final ProductTypeReadMapper productTypeReadMapper;

    @Override
    public ProductReadDto map(Product object) {

        CategoryReadDto category = Optional.ofNullable(object.getCategory())
                .map(categoryReadMapper::map)
                .orElse(null);

        ProductTypeReadDto productType = Optional.ofNullable(object.getProductType())
                .map(productTypeReadMapper::map)
                .orElse(null);

        Set<ShareReadDto> shares = object.getShares().stream()
                .map(shareReadMapper::map)
                .collect(Collectors.toSet());

        return new ProductReadDto(
                object.getId(),
                object.getPrice(),
                object.getName(),
                object.getColor(),
                object.getDescription(),
                productType,
                category,
                shares
        );
    }
}
