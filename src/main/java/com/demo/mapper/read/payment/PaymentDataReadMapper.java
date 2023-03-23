package com.demo.mapper.read.payment;


import com.demo.dto.read.payment.PaymentDataReadDto;
import com.demo.dto.read.payment.PaymentTypeReadDto;
import com.demo.entity.payment.PaymentData;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentDataReadMapper implements Mapper<PaymentData, PaymentDataReadDto> {

    private final PaymentTypeReadMapper paymentTypeReadMapper;

    @Override
    public PaymentDataReadDto map(PaymentData object) {


        PaymentTypeReadDto paymentType = Optional.ofNullable(object.getPaymentType())
                .map(paymentTypeReadMapper::map)
                .orElse(null);

        return new PaymentDataReadDto(
                object.getId(),
                object.getCardDetails(),
                object.getCardNetwork(),
                object.getPaymentTime(),
                paymentType
        );
    }
}
