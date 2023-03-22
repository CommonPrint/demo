package com.demo.controller.order;

import com.demo.dto.createedit.order.InsureCreateEditDto;
import com.demo.dto.read.order.InsureReadDto;
import com.demo.service.order.InsureService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/insure")
@RequiredArgsConstructor
public class InsureController {

    private final InsureService insureService;

    @GetMapping
    public ResponseEntity<List<InsureReadDto>> findAll() {
        List<InsureReadDto> insures = this.insureService.findAll();

        return new ResponseEntity<List<InsureReadDto>>(insures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public InsureReadDto findById(@PathVariable("id") Long id) {
        return insureService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public InsureReadDto create(@RequestBody InsureCreateEditDto insure) {
        return insureService.create(insure);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<InsureReadDto> update(@PathVariable("id") Long id, @RequestBody InsureCreateEditDto insure) {

        return insureService.update(id, insure);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return insureService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }

}
