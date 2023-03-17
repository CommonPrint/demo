package com.demo.mapper.read;

import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.demo.dto.read.CountryReadDto;
import com.demo.entity.Country;

@Component
@RequiredArgsConstructor
public class CountryReadMapper implements Mapper<Country, CountryReadDto> {
	
	@Override
	public CountryReadDto map(Country country) {
		
		return new CountryReadDto(
					country.getId(),
					country.getCountryName()
				);
	}

	
	
}
