package com.demo.dto.read.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockReadDto {

    Long id;


    Integer quantity;
    LocalDate lastUpdateTime;

    ProductReadDto product;

}
