package com.demo.mapper.createedit.payment;

import com.demo.dto.createedit.payment.PaymentDataCreateEditDto;
import com.demo.entity.payment.PaymentData;
import com.demo.entity.payment.PaymentType;
import com.demo.mapper.Mapper;
import com.demo.repository.payment.PaymentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentDataCreateEditMapper implements Mapper<PaymentDataCreateEditDto, PaymentData> {
    private final PaymentTypeRepository paymentTypeRepository;


    @Override
    public PaymentData map(PaymentDataCreateEditDto object) {
        PaymentData paymentData = new PaymentData();

        copy(object, paymentData);

        return paymentData;
    }

    @Override
    public PaymentData map(PaymentDataCreateEditDto fromObject, PaymentData toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    // Вставим данные
    private void copy(PaymentDataCreateEditDto object, PaymentData paymentData) {

        paymentData.setCardDetails(object.getCardDetails());
        paymentData.setCardNetwork(object.getCardNetwork());
        paymentData.setPaymentTime(object.getPaymentTime());
        paymentData.setPaymentType(getPaymentType(object.getPaymentType()));

    }


    //    Получим "Тип платежа"
    public PaymentType getPaymentType(Long paymentTypeId) {
        return Optional.ofNullable(paymentTypeId)
                .flatMap(paymentTypeRepository::findById)
                .orElse(null);
    }
}
