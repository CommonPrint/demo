package com.demo.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.demo.dto.createedit.CountryCreateEditDto;
import com.demo.dto.read.CountryReadDto;
import com.demo.service.CountryService;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;


@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CountryController {

	private final CountryService countryService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CountryReadDto> findAll() {
		return countryService.getAllCountries();
	}
	
	@GetMapping("/{id}")
	public CountryReadDto findById(@PathVariable("id") Long id) {
		return countryService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CountryReadDto create(@RequestBody CountryCreateEditDto country) {
		System.out.println("Country: " + country);
		return countryService.create(country);
	}


	@PutMapping("/{id}")
	public CountryReadDto update(@PathVariable("id") Long id,
								 @RequestBody CountryCreateEditDto country) {
		return countryService.update(id, country)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return countryService.delete(id)
				? noContent().build()
				: notFound().build();
	}
	
}
