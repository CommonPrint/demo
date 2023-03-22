package com.demo.controller.order;

import com.demo.dto.createedit.order.InsurePropertyCreateEditDto;
import com.demo.dto.read.order.InsurePropertyReadDto;
import com.demo.service.order.InsurePropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/insure-property")
@RequiredArgsConstructor
public class InsurePropertyController {

    private final InsurePropertyService insurePropertyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InsurePropertyReadDto> findAll() {
        return insurePropertyService.findAll();
    }

    @GetMapping("/{id}")
    public InsurePropertyReadDto findById(@PathVariable("id") Long id) {
        return insurePropertyService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public InsurePropertyReadDto create(@RequestBody InsurePropertyCreateEditDto insureProperty) {
        return insurePropertyService.create(insureProperty);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public InsurePropertyReadDto update(@PathVariable("id") Long id,
                                 @RequestBody InsurePropertyCreateEditDto insureProperty) {
        return insurePropertyService.update(id, insureProperty)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return insurePropertyService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
