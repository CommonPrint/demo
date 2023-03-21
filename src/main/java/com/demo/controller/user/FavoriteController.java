package com.demo.controller.user;

import com.demo.dto.createedit.user.FavoriteCreateEditDto;
import com.demo.dto.read.user.FavoriteReadDto;
import com.demo.service.user.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<FavoriteReadDto>> findAll() {
        List<FavoriteReadDto> favorites = this.favoriteService.findAll();

        return new ResponseEntity<List<FavoriteReadDto>>(favorites, HttpStatus.OK);
    }


    // Ищем "Избранное" через id-пользователя, т.к. на одного пользователя - одно избранное
    @GetMapping("/{userId}")
    public FavoriteReadDto findByUserId(@PathVariable("userId") Long userId) {

        return favoriteService.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public FavoriteReadDto create(@RequestBody FavoriteCreateEditDto favorite) {
        return favoriteService.create(favorite);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<FavoriteReadDto> update(@PathVariable("id") Long id, @RequestBody FavoriteCreateEditDto favorite) {

        return favoriteService.update(id, favorite);
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return favoriteService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }
    
}
