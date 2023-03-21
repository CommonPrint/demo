package com.demo.mapper.createedit.user;


import com.demo.dto.createedit.user.CartCreateEditDto;
import com.demo.dto.read.product.ProductReadDto;
import com.demo.entity.product.Product;
import com.demo.entity.user.Cart;
import com.demo.entity.user.User;
import com.demo.mapper.Mapper;
import com.demo.repository.user.UserRepository;
import com.demo.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CartCreateEditMapper implements Mapper<CartCreateEditDto, Cart> {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Override
    public Cart map(CartCreateEditDto object) {
        
        Cart cart = new Cart();

        copy(object, cart);

        return cart;
    }

    @Override
    public Cart map(CartCreateEditDto fromObject, Cart toObject) {
        copy(fromObject, toObject);

        return toObject;
    }


    private void copy(CartCreateEditDto object, Cart cart) {
        cart.setUser(getUser(object.getUserId()));
        cart.setProducts(getProducts(object.getProducts()));
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
