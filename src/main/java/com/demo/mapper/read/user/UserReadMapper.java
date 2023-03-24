package com.demo.mapper.read.user;

import com.demo.dto.read.CityReadDto;
import com.demo.dto.read.user.UserReadDto;
import com.demo.entity.user.User;
import com.demo.mapper.Mapper;
import com.demo.mapper.read.CityReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CityReadMapper cityReadMapper;

    @Override
    public UserReadDto map(User object) {


        CityReadDto city = Optional.ofNullable(object.getCity())
                .map(cityReadMapper::map)
                .orElse(null);

        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getFirstname(),
                object.getLastname(),
                object.getBirthDate(),
                object.getClientAddress(),
                object.getEmail(),
                object.getPassword(),
                object.getPhoneNumber(),
                object.getAvatar(),
                city,
                object.getRoles()
        );
    }

}
