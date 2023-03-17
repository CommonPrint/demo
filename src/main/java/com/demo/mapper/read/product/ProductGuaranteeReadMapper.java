package com.demo.mapper.read.product;

import com.demo.dto.read.product.ProductGuaranteeReadDto;
import com.demo.entity.product.ProductGuarantee;
import com.demo.mapper.Mapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductGuaranteeReadMapper implements Mapper<ProductGuarantee, ProductGuaranteeReadDto> {

    @Override
    public ProductGuaranteeReadDto map(ProductGuarantee object) {

        return new ProductGuaranteeReadDto(
                object.getId(),
                object.getGuaranteePeriod()
        );
    }

}
