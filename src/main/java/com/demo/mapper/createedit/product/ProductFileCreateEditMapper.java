package com.demo.mapper.createedit.product;

import com.demo.dto.createedit.product.ProductFileCreateEditDto;
import com.demo.entity.product.*;
import com.demo.mapper.Mapper;
import com.demo.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductFileCreateEditMapper implements Mapper<ProductFileCreateEditDto, ProductFile> {
    @Override
    public ProductFile map(ProductFileCreateEditDto object) {
        ProductFile productFile = new ProductFile();

        copy(object, productFile);

        return productFile;
    }

    @Override
    public ProductFile map(ProductFileCreateEditDto fromObject, ProductFile toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    // Вставим данные
    private void copy(ProductFileCreateEditDto object, ProductFile productFile) {
        productFile.setUrl(object.getUrl());
    }

}
