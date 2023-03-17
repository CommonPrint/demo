package com.demo.mapper.read.product;


import com.demo.dto.read.CityReadDto;
import com.demo.dto.read.CountryReadDto;
import com.demo.dto.read.product.BrandReadDto;
import com.demo.entity.City;
import com.demo.entity.product.Brand;
import com.demo.mapper.Mapper;
import com.demo.mapper.read.CountryReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BrandReadMapper implements Mapper<Brand, BrandReadDto> {

    @Override
    public BrandReadDto map(Brand object) {

        return new BrandReadDto(
                object.getId(),
                object.getBrandName()
        );
    }

}
