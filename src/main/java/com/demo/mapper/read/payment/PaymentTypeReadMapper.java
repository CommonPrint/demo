package com.demo.mapper.read.payment;

import com.demo.dto.read.payment.PaymentTypeReadDto;
import com.demo.entity.payment.PaymentType;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentTypeReadMapper implements Mapper<PaymentType, PaymentTypeReadDto> {

    @Override
    public PaymentTypeReadDto map(PaymentType object) {

        return new PaymentTypeReadDto(
                object.getId(),
                object.getTypeName()
        );
    }

}
