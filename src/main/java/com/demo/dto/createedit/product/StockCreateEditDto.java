package com.demo.dto.createedit.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockCreateEditDto {

    private Long id;

    private Integer quantity;

    private LocalDate lastUpdateTime;

    private Long productId;
}
