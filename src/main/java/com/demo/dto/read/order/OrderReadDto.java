package com.demo.dto.read.order;

import com.demo.dto.read.payment.PaymentDataReadDto;
import com.demo.dto.read.product.ProductReadDto;
import com.demo.dto.read.user.UserReadDto;
import com.demo.entity.order.Delivery;
import com.demo.entity.order.Insure;
import com.demo.entity.payment.PaymentData;
import com.demo.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReadDto {

    Long id;

    Long orderCode;

    Double cost;

    Long timeCreated;

    InsureReadDto insure;

    DeliveryReadDto delivery;

    UserReadDto user;

    PaymentDataReadDto paymentData;

    Set<ProductReadDto> products;

}
