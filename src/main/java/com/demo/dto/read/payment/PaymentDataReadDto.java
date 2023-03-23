package com.demo.dto.read.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDataReadDto {

    Long id;


    // Если тип оплаты будет "cash", то cardNetwork и cardDetails будут пустыми
    String cardDetails;

    String cardNetwork;

    Instant paymentTime;

    PaymentTypeReadDto paymentType;

}
