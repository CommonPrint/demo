package com.demo.mapper.read.order;

import com.demo.dto.read.order.DeliveryReadDto;
import com.demo.entity.order.Delivery;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryReadMapper implements Mapper<Delivery, DeliveryReadDto> {


    @Override
    public DeliveryReadDto map(Delivery object) {

        return new DeliveryReadDto(
                object.getId(),
                object.getDeliveryType(),
                object.getDeliveryDate(),
                object.getAddress(),
                object.getComment()
        );
    }
}
