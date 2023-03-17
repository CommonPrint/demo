package com.demo.dto.read.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductGuaranteeReadDto {

    Long id;
    String guaranteePeriod;

}
