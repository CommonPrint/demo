package com.demo.mapper.read;

import com.demo.dto.read.CityReadDto;
import com.demo.dto.read.CountryReadDto;
import com.demo.entity.City;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CityReadMapper implements Mapper<City, CityReadDto> {

    private final CountryReadMapper countryReadMapper;

    @Override
    public CityReadDto map(City object) {
        CountryReadDto country = Optional.ofNullable(object.getCountry())
                .map(countryReadMapper::map)
                .orElse(null);

        return new CityReadDto(
                object.getId(),
                object.getCityName(),
                country
        );
    }
}
