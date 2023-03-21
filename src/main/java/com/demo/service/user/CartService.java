package com.demo.service.user;


import com.demo.dto.createedit.user.CartCreateEditDto;
import com.demo.dto.read.product.ProductReadDto;
import com.demo.dto.read.user.CartReadDto;
import com.demo.entity.product.Stock;
import com.demo.mapper.createedit.user.CartCreateEditMapper;
import com.demo.mapper.read.user.CartReadMapper;
import com.demo.repository.user.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartCreateEditMapper cartCreateEditMapper;
    private final CartReadMapper cartReadMapper;


    //	Получить все Корзины
    public List<CartReadDto> findAll() {
        return this.cartRepository.findAll()
                .stream()
                .map(cartReadMapper::map)
                .toList();
    }//findAll()


    //  Получим корзину по id
    public Optional<CartReadDto> findById(Long id) {

        return cartRepository.findById(id)
                .map(cartReadMapper::map);
    }



    //  Получим корзину по id пользователя
    public Optional<CartReadDto> findByUserId(Long id) {

        return cartRepository.findByUserId(id)
                .map(cartReadMapper::map);
    }


    // Создание корзины   
    public CartReadDto create(CartCreateEditDto cartDto) {

        return Optional.of(cartDto)
                .map(dto -> {
                    return cartCreateEditMapper.map(dto);
                })
                .map(cartRepository::save)
                .map(cartReadMapper::map)
                .orElseThrow();

    }//create(UserCreateEditDto userDto)



    // Обновление корзины
    public Optional<CartReadDto> update(Long id, CartCreateEditDto cartDto) {
        return cartRepository.findById(id)
                .map(entity -> {
                    return cartCreateEditMapper.map(cartDto, entity);
                })
                .map(cartRepository::saveAndFlush)
                .map(cartReadMapper::map);
    }



    // Удаление Продукта
    public boolean delete(Long id) {

        return cartRepository.findById(id)
                .map(entity -> {
                    cartRepository.delete(entity);
                    cartRepository.flush();

                    return true;
                })
                .orElse(false);
    }
    
}
