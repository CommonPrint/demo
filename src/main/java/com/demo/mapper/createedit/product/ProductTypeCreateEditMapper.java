package com.demo.mapper.createedit.product;

import com.demo.dto.createedit.product.BrandCreateEditDto;
import com.demo.dto.createedit.product.ProductTypeCreateEditDto;
import com.demo.entity.product.Brand;
import com.demo.entity.product.ProductType;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductTypeCreateEditMapper implements Mapper<ProductTypeCreateEditDto, ProductType> {
    @Override
    public ProductType map(ProductTypeCreateEditDto object) {
        ProductType productType = new ProductType();

        copy(object, productType);

        return productType;
    }

    @Override
    public ProductType map(ProductTypeCreateEditDto fromObject, ProductType toObject) {
        copy(fromObject, toObject);

        return toObject;
    }


    // Копируем и вставляем данные
    private void copy(ProductTypeCreateEditDto object, ProductType productType) {
        productType.setTypeName(object.getTypeName());
    }
}
