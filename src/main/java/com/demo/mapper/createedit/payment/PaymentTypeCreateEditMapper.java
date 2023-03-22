package com.demo.mapper.createedit.payment;

import com.demo.dto.createedit.payment.PaymentTypeCreateEditDto;
import com.demo.entity.payment.PaymentType;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PaymentTypeCreateEditMapper implements Mapper<PaymentTypeCreateEditDto, PaymentType> {
    @Override
    public PaymentType map(PaymentTypeCreateEditDto object) {
        PaymentType paymentType = new PaymentType();

        copy(object, paymentType);

        return paymentType;
    }

    @Override
    public PaymentType map(PaymentTypeCreateEditDto fromObject, PaymentType toObject) {
        copy(fromObject, toObject);

        return toObject;
    }


    // Копируем и вставляем данные
    private void copy(PaymentTypeCreateEditDto object, PaymentType paymentType) {
        paymentType.setTypeName(object.getTypeName());
    }
}
