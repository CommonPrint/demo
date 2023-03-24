package com.demo.mapper.createedit.product;

import com.demo.dto.createedit.product.ProductCreateEditDto;
import com.demo.dto.read.product.ProductFileReadDto;
import com.demo.dto.read.product.ProductReviewReadDto;
import com.demo.dto.read.product.ShareReadDto;
import com.demo.entity.product.*;
import com.demo.mapper.Mapper;
import com.demo.repository.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Component
@RequiredArgsConstructor
public class ProductCreateEditMapper implements Mapper<ProductCreateEditDto, Product> {

    private final CategoryRepository categoryRepository;
    private final ShareRepository shareRepository;
    private final ProductTypeRepository productTypeRepository;

    private final ProductGuaranteeRepository productGuaranteeRepository;
    private final ProductFileRepository productFileRepository;

    private final ProductReviewRepository productReviewRepository;

    @Override
    public Product map(ProductCreateEditDto object) {
        Product product = new Product();

        copy(object, product);

        return product;
    }

    @Override
    public Product map(ProductCreateEditDto fromObject, Product toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    // Вставим данные
    private void copy(ProductCreateEditDto object, Product product) {
        product.setName(object.getName());
        product.setPrice(object.getPrice());
        product.setColor(object.getColor());
        product.setDescription(object.getDescription());
        product.setCategory(getCategory(object.getCategoryId()));
        product.setShares(getShares(object.getShares()));
        product.setProductType(getProductType(object.getProductTypeId()));
        product.setProductGuarantee(getProductGuarantee(object.getProductGuaranteeId()));
        product.setFiles(getFiles(object.getFiles()));
        product.setReviews(getReviews(object.getReviews()));
    }

    // Получим Категорию Продукта
    public Category getCategory(Long categoryId) {

        return Optional.ofNullable(categoryId)
                .flatMap(categoryRepository::findById)
                .orElse(null);
    }


    // Получим Aкции и Скидки для продукта
    public Set<Share> getShares(Set<ShareReadDto> shares) {

        Set<Share> shareSet = new HashSet<>();

        shares.forEach(shareReadDto -> {
            shareSet.add(
                    shareRepository.findById(shareReadDto.getId()).orElse(null)
            );
        });

        return shareSet;

    }


    // Получим Изображения для продукта
    public Set<ProductFile> getFiles(Set<ProductFileReadDto> files) {

        Set<ProductFile> productFileSet = new HashSet<>();

        files.forEach(fileReadDto -> {
            productFileSet.add(
                    productFileRepository.findById(fileReadDto.getId()).orElse(null)
            );
        });

        return productFileSet;

    }

    public Set<ProductReview> getReviews(Set<ProductReviewReadDto> reviews) {

        Set<ProductReview> productReviewSet = new HashSet<>();

        reviews.forEach(reviewReadDto -> {
            productReviewSet.add(
                    productReviewRepository.findById(reviewReadDto.getId()).orElse(null)
            );
        });

        return productReviewSet;

    }


    //    Получим "Тип продукта" (компьютер, телевизор, смартфон)
    public ProductType getProductType(Long productTypeId) {
        return Optional.ofNullable(productTypeId)
                .flatMap(productTypeRepository::findById)
                .orElse(null);
    }


    //    Получим "Гарантию продукта" (1 год, 2 года и тд)
    public ProductGuarantee getProductGuarantee(Long productGuaranteeId) {
        return Optional.ofNullable(productGuaranteeId)
                .flatMap(productGuaranteeRepository::findById)
                .orElse(null);
    }
}
