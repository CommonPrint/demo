package com.demo.mapper.createedit.product;

import com.demo.dto.createedit.product.CategoryCreateEditDto;
import com.demo.entity.product.Category;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CategoryCreateEditMapper implements Mapper<CategoryCreateEditDto, Category> {
    @Override
    public Category map(CategoryCreateEditDto object) {
        Category category = new Category();

        copy(object, category);

        return category;
    }

    @Override
    public Category map(CategoryCreateEditDto fromObject, Category toObject) {
        copy(fromObject, toObject);

        return toObject;
    }


    // Копируем и вставляем данные
    private void copy(CategoryCreateEditDto object, Category category) {
        category.setCategoryName(object.getCategoryName());
    }
}
