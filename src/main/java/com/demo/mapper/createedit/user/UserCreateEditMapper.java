package com.demo.mapper.createedit.user;

import com.demo.dto.createedit.user.UserCreateEditDto;
import com.demo.entity.City;
import com.demo.entity.user.User;
import com.demo.mapper.Mapper;
import com.demo.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final CityRepository cityRepository;

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();

        copy(object, user);

        return user;
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {

        System.out.println("User: " + toObject);
        System.out.println("UserEdit: " + fromObject);

        copy(fromObject, toObject);
        return toObject;
    }

    // Вставим данные
    private void copy(UserCreateEditDto object, User user) {
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setEmail(object.getEmail());
        user.setBirthDate(object.getBirthDate());
        user.setClientAddress(object.getClientAddress());
        user.setPassword(object.getPassword());
        user.setPhoneNumber(object.getPhoneNumber());

        user.setCity(getCity(object.getCityId()));
    }


    // Получим город пользователя
    public City getCity(Long cityId) {
        return Optional.ofNullable(cityId)
                .flatMap(cityRepository::findById)
                .orElse(null);
    }

}
