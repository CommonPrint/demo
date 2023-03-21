package com.demo.mapper.createedit.product;

import com.demo.dto.createedit.product.ProductCreateEditDto;
import com.demo.dto.createedit.product.ProductReviewCreateEditDto;
import com.demo.dto.read.product.ShareReadDto;
import com.demo.entity.product.*;
import com.demo.entity.user.User;
import com.demo.mapper.Mapper;
import com.demo.repository.UserRepository;
import com.demo.repository.product.CategoryRepository;
import com.demo.repository.product.ProductRepository;
import com.demo.repository.product.ProductTypeRepository;
import com.demo.repository.product.ShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProductReviewCreateEditMapper implements Mapper<ProductReviewCreateEditDto, ProductReview> {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Override
    public ProductReview map(ProductReviewCreateEditDto object) {
        ProductReview product = new ProductReview();

        copy(object, product);

        return product;
    }

    @Override
    public ProductReview map(ProductReviewCreateEditDto fromObject, ProductReview toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    // Вставим данные
    private void copy(ProductReviewCreateEditDto object, ProductReview review) {
        review.setRating(object.getRating());
        review.setDignity(object.getDignity());
        review.setFlaws(object.getFlaws());
        review.setTextReview(object.getTextReview());
        review.setDateReview(object.getDateReview());
        review.setProduct(getProduct(object.getProductId()));
        review.setUser(getUser(object.getUserId()));
    }

    // Получим продукт к отзыву
    public Product getProduct(Long productId) {

        return Optional.ofNullable(productId)
                .flatMap(productRepository::findById)
                .orElse(null);
    }

    // Получим пользователя отзыва
    public User getUser(Long userId) {

        return Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElse(null);
    }

}
