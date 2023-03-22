package com.demo.mapper.read.order;

import com.demo.dto.read.order.InsurePropertyReadDto;
import com.demo.dto.read.order.InsureReadDto;
import com.demo.entity.order.Insure;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InsureReadMapper implements Mapper<Insure, InsureReadDto> {

    private final InsurePropertyReadMapper insurePropertyReadMapper;

    @Override
    public InsureReadDto map(Insure object) {

        Set<InsurePropertyReadDto> properties = object.getProperties().stream()
                .map(insurePropertyReadMapper::map)
                .collect(Collectors.toSet());

        return new InsureReadDto(
                object.getId(),
                object.getTypeName(),
                object.getDescription(),
                object.getPrice(),
                properties
        );
    }
}
