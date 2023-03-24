package com.demo.controller.user;

import com.demo.dto.createedit.user.UserCreateEditDto;
import com.demo.dto.read.user.UserReadDto;
import com.demo.entity.google_cloud.InputFile;
import com.demo.service.google_cloud.FileServiceImpl;
import com.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FileServiceImpl fileService;

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

    @PostMapping(value="set-avatar", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void addAvatar(@RequestParam("files") MultipartFile[] files, @RequestParam("userId") Long userId){

        Optional<UserReadDto> user = null;

        List<InputFile> result = fileService.uploadFiles(files);

        if(result != null) {

            System.out.println("File name: " + result.get(0).getFileName());
            System.out.println("File url: " + result.get(0).getFileUrl());


            userService.setUserAvatar(result.get(0).getFileUrl(), userId);

            System.out.println("Avatar has been added");
        }

        else {
            System.out.println("Avatar don't added");
        }

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
