package com.demo.service;

import com.demo.dto.createedit.CityCreateEditDto;
import com.demo.dto.createedit.user.UserCreateEditDto;
import com.demo.dto.read.CityReadDto;
import com.demo.entity.user.User;
import com.demo.mapper.createedit.CityCreateEditMapper;
import com.demo.mapper.createedit.user.UserCreateEditMapper;
import com.demo.mapper.read.CityReadMapper;
import com.demo.mapper.read.user.UserReadMapper;
import com.demo.repository.CityRepository;
import com.demo.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityReadMapper cityReadMapper;
    private final CityCreateEditMapper cityCreateEditMapper;


    private final UserRepository userRepository;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserReadMapper userReadMapper;


    public List<CityReadDto> findAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(cityReadMapper::map)
                .toList();
    }


    public List<CityReadDto> findAllByCountryId(Long countryId) {
        System.out.println("Cities by country: " + cityRepository.findAllByCountryId(countryId));

        List<CityReadDto> list = cityRepository.findAllByCountryId(countryId)
                                    .stream()
                                    .map(cityReadMapper::map)
                                    .toList();
        return list;
    }



//    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<CityReadDto> findById(Long id) {

        return cityRepository.findById(id)
                .map(cityReadMapper::map);

    }//findById(Long id)


    //    Создать город
    public CityReadDto create(CityCreateEditDto cityDto) {
        return Optional.of(cityDto)
                .map(dto -> {
                    return cityCreateEditMapper.map(dto);
                })
                .map(cityRepository::save)
                .map(cityReadMapper::map)
                .orElseThrow();
    }


    // Обновить Город
    public Optional<CityReadDto> update(Long id, CityCreateEditDto cityDto) {

        return cityRepository.findById(id)
                .map(entity -> {
                    return cityCreateEditMapper.map(cityDto, entity);
                })
                .map(cityRepository::saveAndFlush)
                .map(cityReadMapper::map);

    }

    // Удалить Город
    public boolean delete(Long id) {

        List<User> users = userRepository.findAllByCityId(id);

        boolean isDelete = cityRepository.findById(id)
                .map(entity -> {
                    cityRepository.delete(entity);
                    cityRepository.flush();

                    return true;
                })
                .orElse(false);

        // Если город удалился, то установим значение null у cityId пользователей
        if(isDelete == true) {

            users.stream()
                    .forEach(user -> {

                        //    В конце указываем, что города нет и значение city_id у пользователя будет null
                        UserCreateEditDto userEdit = new UserCreateEditDto(
                                user.getId(),
                                user.getFirstname(),
                                user.getLastname(),
                                user.getBirthDate(),
                                user.getClientAddress(),
                                user.getPassword(),
                                user.getEmail(),
                                user.getPhoneNumber(),
                                null
                        );
                        userCreateEditMapper.map(userEdit, user);

                        userRepository.saveAndFlush(user);

                        userReadMapper.map(user);
                });

        }

        return isDelete;

    }

}
