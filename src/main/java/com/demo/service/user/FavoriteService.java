package com.demo.service.user;


import com.demo.dto.createedit.user.FavoriteCreateEditDto;
import com.demo.dto.read.user.FavoriteReadDto;
import com.demo.mapper.createedit.user.FavoriteCreateEditMapper;
import com.demo.mapper.read.user.FavoriteReadMapper;
import com.demo.repository.user.FavoriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final FavoriteCreateEditMapper favoriteCreateEditMapper;
    private final FavoriteReadMapper favoriteReadMapper;


    //	Получить все Корзины
    public List<FavoriteReadDto> findAll() {
        return this.favoriteRepository.findAll()
                .stream()
                .map(favoriteReadMapper::map)
                .toList();
    }//findAll()


    //  Получим корзину по id
    public Optional<FavoriteReadDto> findById(Long id) {

        return favoriteRepository.findById(id)
                .map(favoriteReadMapper::map);
    }



    //  Получим избронное по id пользователя
    public Optional<FavoriteReadDto> findByUserId(Long id) {

        return favoriteRepository.findByUserId(id)
                .map(favoriteReadMapper::map);
    }


    // Создание корзины   
    public FavoriteReadDto create(FavoriteCreateEditDto favoriteDto) {

        return Optional.of(favoriteDto)
                .map(dto -> {
                    return favoriteCreateEditMapper.map(dto);
                })
                .map(favoriteRepository::save)
                .map(favoriteReadMapper::map)
                .orElseThrow();

    }//create(UserCreateEditDto userDto)



    // Обновление корзины
    public Optional<FavoriteReadDto> update(Long id, FavoriteCreateEditDto favoriteDto) {
        return favoriteRepository.findById(id)
                .map(entity -> {
                    return favoriteCreateEditMapper.map(favoriteDto, entity);
                })
                .map(favoriteRepository::saveAndFlush)
                .map(favoriteReadMapper::map);
    }



    // Удаление Продукта
    public boolean delete(Long id) {

        return favoriteRepository.findById(id)
                .map(entity -> {
                    favoriteRepository.delete(entity);
                    favoriteRepository.flush();

                    return true;
                })
                .orElse(false);
    }
    
}
