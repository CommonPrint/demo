package com.demo.dto.createedit.product;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductGuaranteeCreateEditDto {

    private Long id;
    private String guaranteePeriod;

}
