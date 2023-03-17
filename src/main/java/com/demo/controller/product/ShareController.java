package com.demo.controller.product;

import com.demo.dto.createedit.product.ShareCreateEditDto;
import com.demo.dto.read.product.ShareReadDto;
import com.demo.service.product.ShareService;

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
@RequestMapping("/api/v1/shares")
@RequiredArgsConstructor
public class ShareController {

    private final ShareService shareService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShareReadDto> findAll() {
        return shareService.findAll();
    }

    @GetMapping( "/{id}")
    public ShareReadDto findById(@PathVariable("id") Long id) {
        return shareService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ShareReadDto create(@RequestBody ShareCreateEditDto share) {
        return shareService.create(share);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ShareReadDto update(@PathVariable("id") Long id,
                                          @RequestBody ShareCreateEditDto share) {
        return shareService.update(id, share)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return shareService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
