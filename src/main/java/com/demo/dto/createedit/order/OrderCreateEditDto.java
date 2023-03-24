package com.demo.dto.createedit.order;

import com.demo.dto.read.product.ProductReadDto;
import com.demo.dto.read.product.ShareReadDto;
import com.demo.entity.order.Insure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateEditDto {

    private Long id;

    private Long orderCode;

    private Double cost;
    private Long timeCreated;

    private Long insureId;
    private Long deliveryId;

    private Long userId;

    private Long paymentDataId;

    private Set<ProductReadDto> products;
}
