package com.demo.mapper.read.product;


import com.demo.dto.read.CountryReadDto;
import com.demo.dto.read.product.ProductReadDto;
import com.demo.dto.read.product.StockReadDto;
import com.demo.entity.product.Stock;
import com.demo.mapper.Mapper;
import com.demo.mapper.read.CountryReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StockReadMapper implements Mapper<Stock, StockReadDto> {

    private final ProductReadMapper productReadMapper;

    @Override
    public StockReadDto map(Stock object) {

        ProductReadDto product = Optional.ofNullable(object.getProduct())
                .map(productReadMapper::map)
                .orElse(null);

        return new StockReadDto(
                object.getId(),
                object.getQuantity(),
                object.getLastUpdateTime(),
                product
        );
    }

}
