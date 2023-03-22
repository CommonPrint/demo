package com.demo.mapper.read.order;


import com.demo.dto.read.order.InsurePropertyReadDto;
import com.demo.entity.order.InsureProperty;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InsurePropertyReadMapper implements Mapper<InsureProperty, InsurePropertyReadDto> {

    @Override
    public InsurePropertyReadDto map(InsureProperty object) {

        return new InsurePropertyReadDto(
                object.getId(),
                object.getProperty()
        );
    }

}
