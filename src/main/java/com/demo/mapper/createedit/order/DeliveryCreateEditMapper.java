package com.demo.mapper.createedit.order;

import com.demo.dto.createedit.order.DeliveryCreateEditDto;
import com.demo.entity.order.Delivery;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DeliveryCreateEditMapper implements Mapper<DeliveryCreateEditDto, Delivery> {


    @Override
    public Delivery map(DeliveryCreateEditDto object) {
        Delivery delivery = new Delivery();

        copy(object, delivery);

        return delivery;
    }

    @Override
    public Delivery map(DeliveryCreateEditDto fromObject, Delivery toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    // Вставим данные
    private void copy(DeliveryCreateEditDto object, Delivery delivery) {
        delivery.setDeliveryType(object.getDeliveryType());
        delivery.setDeliveryDate(object.getDeliveryDate());
        delivery.setAddress(object.getAddress());
        delivery.setComment(object.getComment());
    }

}
