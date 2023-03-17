package com.demo.mapper.read.product;

import com.demo.dto.read.product.ProductTypeReadDto;
import com.demo.entity.product.ProductType;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductTypeReadMapper implements Mapper<ProductType, ProductTypeReadDto> {

    @Override
    public ProductTypeReadDto map(ProductType object) {

        return new ProductTypeReadDto(
                object.getId(),
                object.getTypeName()
        );
    }

}
