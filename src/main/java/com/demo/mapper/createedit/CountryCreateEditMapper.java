package com.demo.mapper.createedit;

import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.demo.dto.createedit.CountryCreateEditDto;
import com.demo.entity.Country;

@Component
@RequiredArgsConstructor
public class CountryCreateEditMapper implements Mapper<CountryCreateEditDto, Country> {

	@Override
	public Country map(CountryCreateEditDto object) {
		Country country = new Country();
		
		copy(object, country);
		
		return country;
		
	}//map(CountryCreateEditDto object)

	
	
	@Override
	public Country map(CountryCreateEditDto fromObject, Country toObject) {
		copy(fromObject, toObject);
		
		return toObject;
	}//map(CountryCreateEditDto fromObject, Country toObject)
	
	
	private void copy(CountryCreateEditDto object, Country country) {
		country.setCountryName(object.getCountryName());
	}

}
