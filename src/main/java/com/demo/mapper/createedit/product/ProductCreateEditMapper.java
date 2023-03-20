package com.demo.mapper.createedit.product;

import com.demo.dto.createedit.product.ProductCreateEditDto;
import com.demo.dto.read.product.ShareReadDto;
import com.demo.entity.product.Category;
import com.demo.entity.product.Product;
import com.demo.entity.Country;
import com.demo.entity.product.ProductType;
import com.demo.entity.product.Share;
import com.demo.mapper.Mapper;
import com.demo.repository.CountryRepository;
import com.demo.repository.product.CategoryRepository;
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
    }

    // Получим Категорию Продукта
    public Category getCategory(Long categoryId) {

        System.out.println("Category id: " + categoryId);

        System.out.println("Category: " + categoryRepository.findById(categoryId));

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


    //    Получим "Тип продукта" (компьютер, телевизор, смартфон)
    public ProductType getProductType(Long productTypeId) {
        return Optional.ofNullable(productTypeId)
                .flatMap(productTypeRepository::findById)
                .orElse(null);
    }
}
