package com.demo.mapper.read.product;


import com.demo.dto.read.product.ProductFileReadDto;
import com.demo.dto.read.product.ProductReadDto;
import com.demo.entity.product.ProductFile;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductFileReadMapper implements Mapper<ProductFile, ProductFileReadDto> {
    @Override
    public ProductFileReadDto map(ProductFile object) {

        return new ProductFileReadDto(
                object.getId(),
                object.getUrl(),
                object.isAvatar()
        );

    }
}
