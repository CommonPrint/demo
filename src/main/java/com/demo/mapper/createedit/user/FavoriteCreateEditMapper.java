package com.demo.mapper.createedit.user;


import com.demo.dto.createedit.user.FavoriteCreateEditDto;
import com.demo.dto.read.product.ProductReadDto;
import com.demo.entity.product.Product;
import com.demo.entity.user.Favorite;
import com.demo.entity.user.User;
import com.demo.mapper.Mapper;
import com.demo.repository.product.ProductRepository;
import com.demo.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class FavoriteCreateEditMapper implements Mapper<FavoriteCreateEditDto, Favorite> {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Override
    public Favorite map(FavoriteCreateEditDto object) {
        
        Favorite favorite = new Favorite();

        copy(object, favorite);

        return favorite;
    }

    @Override
    public Favorite map(FavoriteCreateEditDto fromObject, Favorite toObject) {
        copy(fromObject, toObject);

        return toObject;
    }


    private void copy(FavoriteCreateEditDto object, Favorite favorite) {
        favorite.setUser(getUser(object.getUserId()));
        favorite.setProducts(getProducts(object.getProducts()));
    }

    // Получим пользователя Корзины
    public User getUser(Long userId) {

        return Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElse(null);
    }


    // Получим продукты корзины
    public Set<Product> getProducts(Set<ProductReadDto> products) {

        Set<Product> productsSet = new HashSet<>();

        products.forEach(productReadDto -> {
            productsSet.add(
                    productRepository.findById(productReadDto.getId()).orElse(null)
            );
        });

        return productsSet;

    }


}
