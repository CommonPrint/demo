package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.dto.createedit.CountryCreateEditDto;
import com.demo.dto.read.CountryReadDto;
import com.demo.mapper.createedit.CountryCreateEditMapper;
import com.demo.mapper.read.CountryReadMapper;
import com.demo.repository.CountryRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class CountryService {

	private final CountryRepository countryRepo;

	private final CountryReadMapper countryReadMapper;
	private final CountryCreateEditMapper countryCreateEditMapper;
	
	
	public List<CountryReadDto> getAllCountries() {
		
		return countryRepo.findAll()
				.stream()
				.map(countryReadMapper::map)
				.toList();
	}
	
	
	public Optional<CountryReadDto> findById(Long id) {
		
        return countryRepo.findById(id)
        		.map(countryReadMapper::map);
    }
	
	public CountryReadDto create(CountryCreateEditDto countryDto) {
//		Country country = new Country();

		System.out.println("CountryDto: " + countryDto);

        return Optional.of(countryDto)
        		.map(dto -> {
					System.out.println("CountryDto from map: " + dto);
        			return countryCreateEditMapper.map(dto);
        		})
				.map(countryRepo::save)
        		.map(countryReadMapper::map)
        		.orElseThrow();
    }


	// Обновление страны
	public Optional<CountryReadDto> update(Long id, CountryCreateEditDto countryDto) {
		return countryRepo.findById(id)
				.map(entity -> {
					return countryCreateEditMapper.map(countryDto, entity);
				})
				.map(countryRepo::saveAndFlush)
				.map(countryReadMapper::map);
	}
	
	
	// Удаление страны
	public boolean delete(Long id) {
		return countryRepo.findById(id)
				.map(entity -> {
					countryRepo.delete(entity);
					countryRepo.flush();
					
					return true;
				})
				.orElse(false);
	}

	
}
