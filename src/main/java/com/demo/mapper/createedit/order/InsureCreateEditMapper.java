package com.demo.mapper.createedit.order;

import com.demo.dto.createedit.order.InsureCreateEditDto;
import com.demo.dto.read.order.InsurePropertyReadDto;
import com.demo.entity.order.Insure;
import com.demo.entity.order.InsureProperty;
import com.demo.mapper.Mapper;
import com.demo.repository.order.InsurePropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InsureCreateEditMapper implements Mapper<InsureCreateEditDto, Insure> {
    
    private final InsurePropertyRepository insurePropertyRepository;


    @Override
    public Insure map(InsureCreateEditDto object) {
        Insure insure = new Insure();

        copy(object, insure);

        return insure;
    }

    @Override
    public Insure map(InsureCreateEditDto fromObject, Insure toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    // Вставим данные
    private void copy(InsureCreateEditDto object, Insure insure) {
        insure.setTypeName(object.getTypeName());
        insure.setDescription(object.getDescription());
        insure.setPrice(object.getPrice());
        insure.setProperties(getInsureProperties(object.getProperties()));
    }
    

    // Получим свойства страховки
    public Set<InsureProperty> getInsureProperties(Set<InsurePropertyReadDto> shares) {

        Set<InsureProperty> insurePropertySet = new HashSet<>();

        shares.forEach(insurePropertyReadDto -> {
            insurePropertySet.add(
                    insurePropertyRepository.findById(insurePropertyReadDto.getId()).orElse(null)
            );
        });

        return insurePropertySet;

    }

}
