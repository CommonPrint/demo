package com.demo.dto.createedit.payment;

import com.demo.dto.read.product.ShareReadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDataCreateEditDto {

    private Long id;

    private String cardDetails;

    private String cardNetwork;

    private Instant paymentTime;

    private Long paymentType;

}
