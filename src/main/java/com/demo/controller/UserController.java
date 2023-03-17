package com.demo.controller;

import com.demo.dto.createedit.CityCreateEditDto;
import com.demo.dto.createedit.UserCreateEditDto;
import com.demo.dto.read.CityReadDto;
import com.demo.dto.read.UserReadDto;
import com.demo.service.CityService;
import com.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserReadDto>> findAll() {
        List<UserReadDto> users = this.userService.findAllUsers();

        return new ResponseEntity<List<UserReadDto>>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@RequestBody UserCreateEditDto user) {

        return userService.create(user);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<UserReadDto> update(@PathVariable("id") Long id, @RequestBody UserCreateEditDto user) {

        return userService.update(id, user);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return userService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }

}
