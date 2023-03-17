package com.demo.mapper.createedit.product;

import com.demo.dto.createedit.product.ProductGuaranteeCreateEditDto;
import com.demo.entity.product.ProductGuarantee;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductGuaranteeCreateEditMapper implements Mapper<ProductGuaranteeCreateEditDto, ProductGuarantee> {
    @Override
    public ProductGuarantee map(ProductGuaranteeCreateEditDto object) {
        ProductGuarantee productGuarantee = new ProductGuarantee();

        copy(object, productGuarantee);

        return productGuarantee;
    }

    @Override
    public ProductGuarantee map(ProductGuaranteeCreateEditDto fromObject, ProductGuarantee toObject) {
        copy(fromObject, toObject);

        return toObject;
    }


    // Копируем и вставляем данные
    private void copy(ProductGuaranteeCreateEditDto object, ProductGuarantee productGuarantee) {
        productGuarantee.setGuaranteePeriod(object.getGuaranteePeriod());
    }
}
