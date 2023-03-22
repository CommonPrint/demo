package com.demo.dto.read.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryReadDto {

    Long id;

    String deliveryType;

    LocalDate deliveryDate;

    String address;

    String comment;

}
