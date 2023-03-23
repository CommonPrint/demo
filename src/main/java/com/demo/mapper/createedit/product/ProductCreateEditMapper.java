package com.demo.mapper.createedit.product;

import com.demo.dto.createedit.product.ProductCreateEditDto;
import com.demo.dto.read.product.ProductFileReadDto;
import com.demo.dto.read.product.ShareReadDto;
import com.demo.entity.product.*;
import com.demo.entity.Country;
import com.demo.mapper.Mapper;
import com.demo.repository.CountryRepository;
import com.demo.repository.product.CategoryRepository;
import com.demo.repository.product.ProductFileRepository;
import com.demo.repository.product.ProductTypeRepository;
import com.demo.repository.product.ShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductCreateEditMapper implements Mapper<ProductCreateEditDto, Product> {

    private final CategoryRepository categoryRepository;
    private final ShareRepository shareRepository;
    private final ProductTypeRepository productTypeRepository;

    private final ProductFileRepository productFileRepository;

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
        product.setFiles(getFiles(object.getFiles()));
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


    //    Получим "Тип продукта" (компьютер, телевизор, смартфон)
    public ProductType getProductType(Long productTypeId) {
        return Optional.ofNullable(productTypeId)
                .flatMap(productTypeRepository::findById)
                .orElse(null);
    }
}
