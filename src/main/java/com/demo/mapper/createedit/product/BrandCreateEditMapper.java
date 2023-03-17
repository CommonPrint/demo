package com.demo.mapper.createedit.product;

import com.demo.dto.createedit.CityCreateEditDto;
import com.demo.dto.createedit.product.BrandCreateEditDto;
import com.demo.entity.City;
import com.demo.entity.product.Brand;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class BrandCreateEditMapper implements Mapper<BrandCreateEditDto, Brand> {
    @Override
    public Brand map(BrandCreateEditDto object) {
        Brand brand = new Brand();

        copy(object, brand);

        return brand;
    }

    @Override
    public Brand map(BrandCreateEditDto fromObject, Brand toObject) {
        copy(fromObject, toObject);

        return toObject;
    }


    // Копируем и вставляем данные
    private void copy(BrandCreateEditDto object, Brand brand) {
        brand.setBrandName(object.getBrandName());
    }
}
