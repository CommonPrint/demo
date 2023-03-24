package com.demo.mapper.read.product;


import com.demo.dto.read.product.*;
import com.demo.entity.product.Product;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ProductGuaranteeReadMapper productGuaranteeReadMapper;

    private final ProductFileReadMapper productFileReadMapper;

    private final ProductReviewReadMapper productReviewReadMapper;

    @Override
    public ProductReadDto map(Product object) {

        CategoryReadDto category = Optional.ofNullable(object.getCategory())
                .map(categoryReadMapper::map)
                .orElse(null);

        ProductTypeReadDto productType = Optional.ofNullable(object.getProductType())
                .map(productTypeReadMapper::map)
                .orElse(null);

        ProductGuaranteeReadDto productGuarantee = Optional.ofNullable(object.getProductGuarantee())
                .map(productGuaranteeReadMapper::map)
                .orElse(null);

        Set<ShareReadDto> shares = object.getShares().stream()
                .map(shareReadMapper::map)
                .collect(Collectors.toSet());


        Set<ProductFileReadDto> files = object.getFiles().stream()
                .map(productFileReadMapper::map)
                .collect(Collectors.toSet());

        Set<ProductReviewReadDto> reviews = object.getReviews().stream()
                .map(productReviewReadMapper::map)
                .collect(Collectors.toSet());

        return new ProductReadDto(
                object.getId(),
                object.getPrice(),
                object.getName(),
                object.getColor(),
                object.getDescription(),
                productType,
                productGuarantee,
                category,
                shares,
                files,
                reviews
        );
    }
}
