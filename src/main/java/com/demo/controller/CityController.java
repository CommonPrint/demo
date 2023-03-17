package com.demo.controller;

import com.demo.dto.createedit.CityCreateEditDto;
import com.demo.dto.read.CityReadDto;
import com.demo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity<List<CityReadDto>> findAll() {
        List<CityReadDto> cities = this.cityService.findAllCities();

        return new ResponseEntity<List<CityReadDto>>(cities, HttpStatus.OK);
    }


    @GetMapping("/cities/{id}")
    public CityReadDto findById(@PathVariable("id") Long id) {
        return cityService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/country/{countryId}/cities")
    public ResponseEntity<List<CityReadDto>> findCitiesByCountry(@PathVariable("countryId") Long countryId) {

        List<CityReadDto> cities = this.cityService.findAllByCountryId(countryId);

        return new ResponseEntity<List<CityReadDto>>(cities, HttpStatus.OK);
    }




    @PostMapping(value = "/cities", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CityReadDto create(@RequestBody CityCreateEditDto city) {

        return cityService.create(city);
    }


    @PutMapping(value = "/cities/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<CityReadDto> update(@PathVariable("id") Long id, @RequestBody CityCreateEditDto city) {

        return cityService.update(id, city);
    }



    @DeleteMapping("/cities/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return cityService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }

}
