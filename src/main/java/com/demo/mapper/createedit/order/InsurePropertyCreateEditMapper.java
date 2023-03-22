package com.demo.mapper.createedit.order;

import com.demo.dto.createedit.order.InsurePropertyCreateEditDto;
import com.demo.entity.order.InsureProperty;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InsurePropertyCreateEditMapper implements Mapper<InsurePropertyCreateEditDto, InsureProperty> {
    @Override
    public InsureProperty map(InsurePropertyCreateEditDto object) {
        InsureProperty insureProperty = new InsureProperty();

        copy(object, insureProperty);

        return insureProperty;
    }

    @Override
    public InsureProperty map(InsurePropertyCreateEditDto fromObject, InsureProperty toObject) {
        copy(fromObject, toObject);

        return toObject;
    }


    // Копируем и вставляем данные
    private void copy(InsurePropertyCreateEditDto object, InsureProperty insureProperty) {
        insureProperty.setProperty(object.getProperty());
    }
}
