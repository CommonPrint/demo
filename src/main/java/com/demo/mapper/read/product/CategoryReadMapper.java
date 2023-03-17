package com.demo.mapper.read.product;

import com.demo.dto.read.product.CategoryReadDto;
import com.demo.entity.product.Category;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryReadMapper implements Mapper<Category, CategoryReadDto> {

    @Override
    public CategoryReadDto map(Category object) {

        return new CategoryReadDto(
                object.getId(),
                object.getCategoryName()
        );
    }

}
