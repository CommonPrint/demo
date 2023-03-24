package com.demo.mapper.read.order;

import com.demo.dto.read.order.DeliveryReadDto;
import com.demo.dto.read.order.InsureReadDto;
import com.demo.dto.read.order.OrderReadDto;
import com.demo.dto.read.order.OrderReadDto;
import com.demo.dto.read.payment.PaymentDataReadDto;
import com.demo.dto.read.product.CategoryReadDto;
import com.demo.dto.read.product.ProductReadDto;
import com.demo.dto.read.user.UserReadDto;
import com.demo.entity.order.Order;
import com.demo.mapper.Mapper;
import com.demo.mapper.read.payment.PaymentDataReadMapper;
import com.demo.mapper.read.product.CategoryReadMapper;
import com.demo.mapper.read.product.ProductReadMapper;
import com.demo.mapper.read.user.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderReadMapper implements Mapper<Order, OrderReadDto> {

    private final InsureReadMapper insureReadMapper;
    private final ProductReadMapper productReadMapper;

    private final DeliveryReadMapper deliveryReadMapper;

    private final UserReadMapper userReadMapper;

    private final PaymentDataReadMapper paymentDataReadMapper;

    @Override
    public OrderReadDto map(Order object) {

        InsureReadDto insure = Optional.ofNullable(object.getInsure())
                .map(insureReadMapper::map)
                .orElse(null);

        DeliveryReadDto delivery = Optional.ofNullable(object.getDelivery())
                .map(deliveryReadMapper::map)
                .orElse(null);

        UserReadDto user = Optional.ofNullable(object.getUser())
                .map(userReadMapper::map)
                .orElse(null);


        PaymentDataReadDto paymentData = Optional.ofNullable(object.getPaymentData())
                .map(paymentDataReadMapper::map)
                .orElse(null);


        Set<ProductReadDto> products = object.getProducts().stream()
                .map(productReadMapper::map)
                .collect(Collectors.toSet());


        return new OrderReadDto(
                object.getId(),
                object.getOrderCode(),
                object.getCost(),
                object.getTimeCreated(),
                insure,
                delivery,
                user,
                paymentData,
                products
        );

//        return new OrderReadDto(
//                object.getId(),
//                object.getOrderCode(),
//                object.getCost(),
//                object.getTimeCreated()
//        );
    }
}
