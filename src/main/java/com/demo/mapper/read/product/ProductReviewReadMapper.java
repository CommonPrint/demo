package com.demo.mapper.read.product;


import com.demo.dto.read.UserReadDto;
import com.demo.dto.read.product.*;
import com.demo.entity.product.ProductReview;
import com.demo.mapper.Mapper;
import com.demo.mapper.read.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductReviewReadMapper implements Mapper<ProductReview, ProductReviewReadDto> {

    private final ProductReadMapper productReadMapper;
    private final UserReadMapper userReadMapper;

    @Override
    public ProductReviewReadDto map(ProductReview object) {

        UserReadDto user = Optional.ofNullable(object.getUser())
                .map(userReadMapper::map)
                .orElse(null);

        ProductReadDto product = Optional.ofNullable(object.getProduct())
                .map(productReadMapper::map)
                .orElse(null);


        return new ProductReviewReadDto(
                object.getId(),
                object.getRating(),
                object.getDignity(),
                object.getFlaws(),
                object.getTextReview(),
                object.getDateReview(),
                product,
                user
        );
    }
}
