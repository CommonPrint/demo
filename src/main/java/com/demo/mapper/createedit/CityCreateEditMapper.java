package com.demo.mapper.createedit;

import com.demo.dto.createedit.CityCreateEditDto;
import com.demo.entity.City;
import com.demo.entity.Country;
import com.demo.mapper.Mapper;
import com.demo.repository.CountryRepository;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CityCreateEditMapper implements Mapper<CityCreateEditDto, City> {

    private final CountryRepository countryRepository;


    @Override
    public City map(CityCreateEditDto object) {
        City city = new City();

        copy(object, city);

        return city;
    }

    @Override
    public City map(CityCreateEditDto fromObject, City toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    // Вставим данные
    private void copy(CityCreateEditDto object, City city) {
        city.setCityName(object.getCityName());
        city.setCountry(getCountry(object.getCountryId()));
    }

    // Получим страну города
    public Country getCountry(Long countryId) {
        return Optional.ofNullable(countryId)
                .flatMap(countryRepository::findById)
                .orElse(null);
    }
}
